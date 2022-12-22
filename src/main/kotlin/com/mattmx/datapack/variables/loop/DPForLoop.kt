package com.mattmx.datapack.variables.loop

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.util.global
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.ExecuteBuilder
import java.util.*

class DPForLoop(
    val translator: DataPackTranslator,
    val exec: () -> String,
    val times: Int,
    val scheduleTime: ScheduleTime
) {

    fun build(): Triple<String, String, String> {
        val varName = "loop_${times}_" + UUID.randomUUID().toString()
        val fileName = "$varName.mcfunction"
        val variable = DPVariable(translator.mappings, varName, 0)
        val mainList = arrayListOf<String>()
        val functionList = arrayListOf<String>()

        mainList += variable.create()
        mainList += translator.mappings["schedule.create"]!!
            .replace("{name}", "${translator.id}:$fileName")
            .replace("{time}", scheduleTime.toString())
            .replace("{action}", "replace")
        // Make a string to schedule it using @scheduleTime
        // Run the actual code we want
        functionList += exec()
        // Increment the loop timer
        functionList += variable.add(1)
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

        return Triple(fileName, functionList.joinToString("\n"), mainList.joinToString("\n"))
    }

}