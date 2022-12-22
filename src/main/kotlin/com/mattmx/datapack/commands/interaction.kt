package com.mattmx.datapack.commands

import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.colorJson

fun tellraw(mappings: DataPackMappings, target: String, message: String) =
    mappings["tellraw"]!!
        .replace("{target}", target)
        .replace("{json}", message.colorJson())