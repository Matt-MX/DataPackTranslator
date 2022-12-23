package com.mattmx.datapack.objects.loop

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.FunctionBuilder

class LoopInvocation(translator: DataPackTranslator, private val loop: DPLoop) : FunctionBuilder(translator) {

    fun cancel() : String =
        translator.mappings["schedule.clear"]!!
            .replace("{name}", loop.varName)

    fun varName() = loop.varName

    fun counterVariable() = loop.variable

}