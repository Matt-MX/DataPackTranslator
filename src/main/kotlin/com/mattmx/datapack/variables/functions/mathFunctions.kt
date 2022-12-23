package com.mattmx.datapack.variables.functions

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.variables.DPVariable

fun FunctionBuilder.sqrt(x: DPVariable) =
    functionWrapper("sqrt") {
        namespace = "nnmath"
        path = "sqrt/exe"
        out = "nnmath_sqrt" to "out"
        params += "nnmath_sqrt" to "in"
    }.invoke(this, x)

fun FunctionBuilder.random(min: Any, max: Any) =
    functionWrapper("rand") {
        namespace = "nnmath"
        path = "rand/exe"
        out = "nnmath_sqrt" to "out"
        params += "nnmath_sqrt" to "min"
        params += "nnmath_sqrt" to "max"
    }.invoke(this, min, max)