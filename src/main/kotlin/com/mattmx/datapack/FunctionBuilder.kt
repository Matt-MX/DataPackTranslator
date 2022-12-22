package com.mattmx.datapack

import com.mattmx.datapack.util.json
import com.mattmx.datapack.variables.DPList
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.ExecuteBuilder
import com.mattmx.datapack.variables.executes.ExecuteCondition
import com.mattmx.datapack.variables.executes.IfCondition
import com.mattmx.datapack.variables.loop.DPForLoop
import com.mattmx.datapack.variables.loop.DPWhileLoop
import com.mattmx.datapack.variables.loop.LoopInvocation
import com.mattmx.datapack.variables.loop.ScheduleTime
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

open class FunctionBuilder(val translator: DataPackTranslator, val builder: ArrayList<String> = arrayListOf()) {

    var runOnLoad = false
    var runOnTick = false

    operator fun plusAssign(line: String) {
        builder += line
    }

    fun tellraw(target: String, value: Component) {
        builder += com.mattmx.datapack.commands.tellraw(translator.mappings, target, value.json())
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

    fun variable(name: String, default: Any? = null) : DPVariable = DPVariable(translator.mappings, this, name, default)

    fun comment(value: String) {
        builder += translator.mappings["comment"]!! + value
    }

    fun call(function: String) {
        builder +=  translator.mappings["function.call"]!!
            .replace("{name}", "${translator.id}:$function")
    }

    fun schedule(function: String, time: ScheduleTime) {
        builder +=  translator.mappings["schedule.create"]!!
            .replace("{name}", "${translator.id}:$function")
            .replace("{time}", time.toString())
            .replace("{action}", "replace")
    }

    fun execIf(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionIf(conditions.joinToString(" if ")) }, builder)

    fun execUnless(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        exec({ it.conditionIf(conditions.joinToString(" unless ")) }, builder)

    private inline fun exec(condition: (ExecuteBuilder) -> ExecuteBuilder, builder: ExecuteBuilder.() -> Unit) : ExecuteBuilder {
        val execute = ExecuteBuilder(this)
        val toInvoke = condition(execute)
        builder.invoke(toInvoke)
        return execute
    }

    fun list(name: String) = DPList(name, this)

    fun lines() = builder

    override fun toString(): String = builder.joinToString("\n")
}

inline fun functionBuilder(translator: DataPackTranslator, builder: FunctionBuilder.() -> Unit) : FunctionBuilder {
    val function = FunctionBuilder(translator)
    builder(function)
    return function
}