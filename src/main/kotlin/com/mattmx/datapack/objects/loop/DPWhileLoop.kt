package com.mattmx.datapack.objects.loop

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.util.global
import com.mattmx.datapack.objects.DPVariable
import com.mattmx.datapack.objects.executes.ExecuteBuilder
import java.util.*

class DPWhileLoop(
    val translator: DataPackTranslator,
    inline val exec: LoopInvocation.() -> Unit,
    val shouldContinue: String?,
    val scheduleTime: ScheduleTime
) : DPLoop() {
    override var varName = "loop_while_" + UUID.randomUUID().toString()

    fun build(): Triple<String, List<String>, List<String>> {
        val fileName = "${translator.config.whileLoopStorage}$varName.mcfunction"
        val mainList = arrayListOf<String>()
        val functionList = arrayListOf<String>()

        // Make a string to schedule it using @scheduleTime
        // Run the actual code we want
        val functionBuilder = LoopInvocation(translator, this)
        exec(functionBuilder)
        functionList += functionBuilder.toString()

        variable = DPVariable(functionBuilder, varName, initial = 0)

        mainList += variable.createString().split("\n")
        // The following line will initialize the first execution of the loop, the file will recursively call itself
        mainList += translator.mappings["schedule.create"]!!
            .replace("{name}", "${translator.id}:$fileName")
            .replace("{time}", scheduleTime.toString())
            .replace("{action}", "replace")
        // Increment the loop timer
        functionList += variable.addString(1)
        // todo check if we need to cancel
        shouldContinue?.let {

        }
        functionList += ExecuteBuilder(functionBuilder)
            .run {
                this += translator.mappings["schedule.create"]!!
                    .replace("{name}", "${translator.id}:$fileName")
                    .replace("{time}", scheduleTime.toString())
                    .replace("{action}", "replace")
            }.toString()

        return Triple(fileName, functionList, mainList)
    }

}