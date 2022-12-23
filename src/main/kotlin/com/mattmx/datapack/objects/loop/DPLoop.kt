package com.mattmx.datapack.objects.loop

import com.mattmx.datapack.objects.DPVariable

abstract class DPLoop {
    open lateinit var varName: String
    open lateinit var variable: DPVariable
}