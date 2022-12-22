package com.mattmx.datapack

import com.mattmx.datapack.variables.DPList
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.loop.DPForLoop
import com.mattmx.datapack.variables.loop.ScheduleTime

class FunctionBuilder(val translator: DataPackTranslator, private val builder: ArrayList<String> = arrayListOf()) {

    var runOnLoad = false
    var runOnTick = false

    operator fun plusAssign(line: String) {
        builder += line
    }

    fun tellraw(target: String, value: String) {
        builder += com.mattmx.datapack.commands.tellraw(translator.mappings, target, value)
    }

    fun repeat(times: Int, period: ScheduleTime = ScheduleTime(1, 't'), str: FunctionBuilder.() -> Unit) {
        val ret = DPForLoop(translator, str, times, period).build()
        builder += ret.third
        translator.functions[ret.first] = FunctionBuilder(translator, ArrayList(ret.second))
    }

    fun repeat(period: ScheduleTime = ScheduleTime(1, 't'), str: FunctionBuilder.() -> Unit) {

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

    fun list(name: String) = DPList(name, this)

    override fun toString(): String = builder.joinToString("\n")
}

inline fun functionBuilder(translator: DataPackTranslator, builder: FunctionBuilder.() -> Unit) : FunctionBuilder {
    val function = FunctionBuilder(translator)
    builder(function)
    return function
}