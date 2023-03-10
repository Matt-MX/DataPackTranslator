package com.mattmx.datapack.objects.loop

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.util.global
import com.mattmx.datapack.objects.DPVariable
import com.mattmx.datapack.objects.executes.ExecuteBuilder
import com.mattmx.datapack.util.funcNextRef
import java.util.*

class DPForLoop(
    val translator: DataPackTranslator,
    val calledFrom: String,
    inline val exec: LoopInvocation.() -> Unit,
    val times: Int,
    val scheduleTime: ScheduleTime
) : DPLoop() {
    override var varName = "loop_${times}_" + UUID.randomUUID().toString()

    fun build(): Triple<String, List<String>, List<String>> {
//        val fileName = "${translator.config.forLoopStorage}$varName.mcfunction"
        val mainList = arrayListOf<String>()
        val fileName = "fors/$varName.mcfunction"
        val functionList = arrayListOf<String>()

        // Make a string to schedule it using @scheduleTime
        // Run the actual code we want
        val functionBuilder = LoopInvocation(translator, this)
        variable = DPVariable(functionBuilder, varName, initial = 0)
        exec(functionBuilder)
        functionList += functionBuilder.toString()

        mainList += variable.createString().split("\n")
        // The following line will initialize the first execution of the loop, the file will recursively call itself
        mainList += translator.mappings["function.call"]!!
            .replace("{name}", "${translator.id}:$fileName")
        // Increment the loop timer
        functionList += variable.addString(1)
        // Add a condition to check if we need to cancel at the end of the loop
        functionList += ExecuteBuilder(functionBuilder)
            .conditionIf("score $global $varName matches $times")
            .run {
                call("${translator.id}:${calledFrom}_$funcNextRef")
                this += translator.mappings["schedule.clear"]!!
                    .replace("{name}", "${translator.id}:$fileName")
                this += variable.destroyString()
            }.toString()
        functionList += ExecuteBuilder(functionBuilder)
            .conditionUnless("score $global $varName matches $times")
            .run {
                if (scheduleTime.amount == 0) {
                    // Call function immediately
                    call("${translator.id}:$fileName")
                } else {
                    schedule("${translator.id}:$fileName", scheduleTime)
                }
            }.toString()

        return Triple(fileName, functionList, mainList)
    }

}