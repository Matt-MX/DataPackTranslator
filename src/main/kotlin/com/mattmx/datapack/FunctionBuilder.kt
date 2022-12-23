package com.mattmx.datapack

import com.mattmx.datapack.enums.EffectAction
import com.mattmx.datapack.objects.Location
import com.mattmx.datapack.enums.TitleType
import com.mattmx.datapack.util.json
import com.mattmx.datapack.variables.DPList
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.ExecuteBuilder
import com.mattmx.datapack.variables.executes.location
import com.mattmx.datapack.variables.executes.selector.EntitySelector
import com.mattmx.datapack.variables.executes.selector.GameMode
import com.mattmx.datapack.variables.executes.selector.selected
import com.mattmx.datapack.variables.loop.DPForLoop
import com.mattmx.datapack.variables.loop.DPWhileLoop
import com.mattmx.datapack.variables.loop.LoopInvocation
import com.mattmx.datapack.variables.loop.ScheduleTime
import net.kyori.adventure.text.Component

open class FunctionBuilder(val translator: DataPackTranslator, val builder: ArrayList<String> = arrayListOf()) {
    val mappings = translator.mappings
    var runOnLoad = false
    var runOnTick = false

    operator fun plusAssign(line: String) {
        builder += line
    }

    fun tellraw(target: EntitySelector, message: Component) {
        builder += mappings["tellraw"]!!
            .replace("{target}", target.build())
            .replace("{json}", message.json())
    }

    fun title(target: EntitySelector, type: TitleType, message: Component) {
        builder += mappings["title"]!!
            .replace("{target}", target.build())
            .replace("{type}", type.name.lowercase())
            .replace("{json}", message.json())
    }

    fun title(target: EntitySelector, title: Component, subtitle: Component? = null) {
        title(target, TitleType.TITLE, title)
        subtitle?.let { title(target, TitleType.SUBTITLE, it) }
    }

    fun teleport(target: EntitySelector, location: Location) {
        builder += mappings["teleport"]!!
            .replace("{target}", target.build())
            .replace("{location}", location.toString())
    }

    fun teleport(target: EntitySelector, location: EntitySelector) {
        builder += mappings["teleport"]!!
            .replace("{target}", target.build())
            .replace("{location}", location.build())
    }

    fun effect(
        action: EffectAction,
        target: EntitySelector,
        name: String,
        time: Int = 30,
        level: Int = 0,
        hide: Boolean = false
    ) {
        builder += mappings["effect"]!!
            .replace("{action}", action.name.lowercase())
            .replace("{target}", target.build())
            .replace("{name}", if (name.contains(":")) name else "minecraft:$name")
            .replace("{time}", time.toString())
            .replace("{level}", level.toString())
            .replace("{hide}", hide.toString())
    }

    fun gameMode(target: EntitySelector, gameMode: GameMode) {
        builder += mappings["gamemode"]!!
            .replace("{target}", target.build())
            .replace("{mode}", gameMode.name.lowercase())
    }

    fun repeat(times: Int, period: ScheduleTime = ScheduleTime(1, 't'), str: LoopInvocation.() -> Unit) {
        val ret = DPForLoop(translator, str, times, period).build()
        builder += ret.third
        translator.functions[ret.first] = FunctionBuilder(translator, ArrayList(ret.second))
    }

    fun repeat(period: ScheduleTime = ScheduleTime(1, 't'), str: FunctionBuilder.() -> Unit) {
        val ret = DPWhileLoop(translator, str, null, period).build()
        builder += ret.third
        translator.functions[ret.first] = FunctionBuilder(translator, ArrayList(ret.second))
    }

    fun variable(name: String, type: String = "dummy", default: Int? = null): DPVariable =
        DPVariable(this, name, type = type, initial = default)

    fun variable(name: String, selector: EntitySelector = selected(), type: String = "dummy", default: Int? = null): DPVariable =
        DPVariable(this, name, selector.build(), type, default)

    fun comment(value: String) {
        builder += translator.mappings["comment"]!! + value
    }

    fun call(function: String) {
        builder += translator.mappings["function.call"]!!
            .replace("{name}", "${translator.id}:$function")
    }

    fun schedule(function: String, time: ScheduleTime) {
        builder += translator.mappings["schedule.create"]!!
            .replace("{name}", "${translator.id}:$function")
            .replace("{time}", time.toString())
            .replace("{action}", "replace")
    }

    fun execIf(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionIf(conditions.joinToString(" if ")) }, builder)

    fun execUnless(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionIf(conditions.joinToString(" unless ")) }, builder)

    fun execAs(condition: EntitySelector, builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionAs(condition) }, builder)

    fun execAt(condition: EntitySelector = selected(), builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionAt(condition) }, builder)

    fun execStore(action: String = "result", builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionStore(action) }, builder)

    private inline fun exec(
        condition: (ExecuteBuilder) -> ExecuteBuilder,
        builder: ExecuteBuilder.() -> Unit
    ): ExecuteBuilder {
        val execute = ExecuteBuilder(this)
        val toInvoke = condition(execute)
        builder.invoke(toInvoke)
        return execute
    }

    fun list(name: String) = DPList(name, this)

    fun lines() = builder

    override fun toString(): String = builder.joinToString("\n")
}

inline fun functionBuilder(translator: DataPackTranslator, builder: FunctionBuilder.() -> Unit): FunctionBuilder {
    val function = FunctionBuilder(translator)
    builder(function)
    return function
}