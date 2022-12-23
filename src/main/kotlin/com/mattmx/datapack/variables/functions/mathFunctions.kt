package com.mattmx.datapack.variables.functions

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.util.global
import com.mattmx.datapack.variables.DPVariable

fun FunctionBuilder.sqrt(x: DPVariable, y: DPVariable) =
    FunctionWrapper("sqrt", "util/math/sqrt/exe", "first" to global, "second" to global)
        .invoke(this, x, y)