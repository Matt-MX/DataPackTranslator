package com.mattmx.datapack.variables

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.global

class DPVariable(val mappings: DataPackMappings, var function: FunctionBuilder, val id: String, initial: Any? = null) {
    private var value: Any? = initial

    fun createString() =
        mappings["variable.create"]!!
            .replace("{id}", id) +
                if (value != null)
                    "\n" + mappings["variable.assign"]!!
                        .replace("{target}", global)
                        .replace("{id}", id)
                        .replace("{value}", value.toString())
                else ""

    fun setString(value: Any) =
        mappings["variable.assign"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun destroyString() =
        mappings["variable.destroy"]!!
            .replace("{target}", global)
            .replace("{id}", id)


    fun addString(value: Int) =
        mappings["variable.add"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun minusString(value: Int) =
        mappings["variable.remove"]!!
            .replace("{target}", global)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun displayNameString(value: String) =
        mappings["variable.displayName"]!!
            .replace("{id}", id)
            .replace("{value}", value)

    fun renderTypeString(value: RenderType) =
        mappings["variable.displayType"]!!
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun setDisplayString(value: ScoreboardDisplay) =
        mappings["variable.display"]!!
            .replace("{id}", id)
            .replace("{value}", value.translatable)

    infix fun add(value: Int) {
        function += addString(value)
    }
    fun create() {
        function += createString()
    }
    infix fun minus(value: Int) {
        function += minusString(value)
    }
    fun destroy() {
        function += destroyString()
    }
    infix fun displayName(name: String) {
        function += displayNameString(name)
    }
    infix fun renderType(type: RenderType) {
        function += renderTypeString(type)
    }
    infix fun display(display: ScoreboardDisplay) {
        function += setDisplayString(display)
    }
    infix fun set(value: Any) {
        function += setString(value)
    }

    operator fun plusAssign(value: Int) = add(value)
    operator fun minusAssign(value: Int) = minus(value)
    operator fun inc(): DPVariable {
        plusAssign(1)
        return this
    }
    operator fun dec() : DPVariable {
        minusAssign(1)
        return this
    }

}