package com.mattmx.datapack.objects

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.global
import com.mattmx.datapack.objects.executes.ExecuteBuilder

class DPList(val name: String, val function: FunctionBuilder) {
    private val sizeVarName = "${name}_size"

    init {
        function.variable("${name}_size").set(0)
    }

    operator fun set(index: Int, value: Any) {
        function.variable("${name}_$index").set(value)
    }

    operator fun get(index: Int) = function.variable("${name}_$index")

}