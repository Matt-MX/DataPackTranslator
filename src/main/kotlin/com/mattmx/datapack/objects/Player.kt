package com.mattmx.datapack.objects

import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.variables.DPTranslatable

class Player(val mappings: DataPackMappings) : DPTranslatable<Player>() {

    fun teleport(x: Float, y: Float, z: Float): String {
        return mappings["teleport"]!!
            .replace("{target}", "@s")
            .replace("{location}", Location(x.toString(), y.toString(), z.toString()).toString())
    }

}