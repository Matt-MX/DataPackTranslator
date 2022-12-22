package com.mattmx.datapack.variables.loop

import com.mattmx.datapack.variables.DPVariable

abstract class DPLoop {
    open lateinit var varName: String
    open lateinit var variable: DPVariable
}