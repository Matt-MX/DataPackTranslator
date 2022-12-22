package com.mattmx.datapack.variables

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.global
import com.mattmx.datapack.variables.executes.ExecuteBuilder

class DPList(val name: String, val function: FunctionBuilder) {
    private val sizeVarName = "${name}_size"

    init {
        function.variable("${name}_size").set(0)
    }

    fun set(index: Int, value: Any) {
        function.variable("${name}_$index").set(value)
    }

}