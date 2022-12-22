package com.mattmx.datapack.variables.loop

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.util.global
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.ExecuteBuilder
import java.util.*

class DPForLoop(
    val translator: DataPackTranslator,
    inline val exec: FunctionBuilder.() -> Unit,
    val times: Int,
    val scheduleTime: ScheduleTime
) {

    fun build(): Triple<String, List<String>, List<String>> {
        val varName = "loop_${times}_" + UUID.randomUUID().toString()
        val fileName = "$varName.mcfunction"
        val mainList = arrayListOf<String>()
        val functionList = arrayListOf<String>()

        // Make a string to schedule it using @scheduleTime
        // Run the actual code we want
        val functionBuilder = FunctionBuilder(translator)
        exec(functionBuilder)
        functionList += functionBuilder.toString()

        val variable = DPVariable(translator.mappings, functionBuilder, varName, 0)

        mainList += variable.createString()
        // The following line will initialize the first execution of the loop, the file will recursively call itself
        mainList += translator.mappings["schedule.create"]!!
            .replace("{name}", "${translator.id}:$fileName")
            .replace("{time}", scheduleTime.toString())
            .replace("{action}", "replace")
        // Increment the loop timer
        functionList += variable.addString(1)
        // Add a condition to check if we need to cancel at the end of the loop
        functionList += ExecuteBuilder()
            .conditionIf("score $global $varName matches $times")
            .run(translator.mappings["schedule.clear"]!!
                .replace("{name}", "${translator.id}:$fileName"))
        functionList += ExecuteBuilder()
            .conditionUnless("score $global $varName matches $times")
            .run(translator.mappings["schedule.create"]!!
                .replace("{name}", "${translator.id}:$fileName")
                .replace("{time}", scheduleTime.toString())
                .replace("{action}", "replace"))

        return Triple(fileName, functionList, mainList)
    }

}