package com.mattmx.datapack.variables

import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.global

class DPVariable(val mappings: DataPackMappings, val id: String, initial: Any? = null) {
    private var value: Any? = initial

    fun create() = mappings["variable.create"]!!
        .replace("{id}", id) +
            if (value != null)
                "\n" + mappings["variable.assign"]!!
                    .replace("{target}", global)
                    .replace("{id}", id)
                    .replace("{value}", value.toString())
            else ""

    fun set(value: Any) =
        mappings["variable.assign"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun remove() =
        mappings["variable.remove"]!!
            .replace("{target}", global)
            .replace("{id}", id)

    fun add(value: Int) =
        mappings["variable.add"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun minus(value: Int) = remove(value)
    fun remove(value: Int) =
        mappings["variable.remove"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun displayName(value: String) =
        mappings["variable.displayName"]!!
            .replace("{id}", id)
            .replace("{value}", value)

    fun renderType(value: RenderType) =
        mappings["variable.displayType"]!!
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun setDisplay(value: ScoreboardDisplay) =
        mappings["variable.display"]!!
            .replace("{id}", id)
            .replace("{value}", value.translatable)

}