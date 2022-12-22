package com.mattmx.datapack.variables

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.global

class DPVariable(val mappings: DataPackMappings, var function: FunctionBuilder, val id: String, initial: Any? = null) {
    private var value: Any? = initial

    init {
        function += createString()
    }

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

    infix fun swap(value: DPVariable) {
        function += mappings["variable.operation"]!!
            .replace("{target1}", global)
            .replace("{target2}", global)
            .replace("{id1}", id)
            .replace("{id2}", value.id)
            .replace("{operation}", "><")
    }

    infix fun set(value: Any) {
        function += setString(value)
    }

    infix fun set(value: DPVariable) {
        function += varOperation(global, id, "=", id2 = value.id)
    }

    operator fun plusAssign(value: Int) = add(value)
    operator fun plusAssign(value: DPVariable) {
        function += varOperation(global, id, "+=", id2 = value.id)
    }

    operator fun minusAssign(value: Int) = minus(value)
    operator fun minusAssign(value: DPVariable) {
        function += varOperation(global, id, "-=", id2 = value.id)
    }

    operator fun divAssign(value: DPVariable) {
        function += varOperation(global, id, "/=", id2 = value.id)
    }

    operator fun timesAssign(value: DPVariable) {
        function += varOperation(global, id, "*=", id2 = value.id)
    }

    operator fun remAssign(value: DPVariable) {
        function += varOperation(global, id, "%=", id2 = value.id)
    }

    operator fun inc(): DPVariable {
        plusAssign(1)
        return this
    }

    operator fun dec(): DPVariable {
        minusAssign(1)
        return this
    }

    operator fun times(y: DPVariable) = returnOperation(y, "*=")
    operator fun plus(y: DPVariable) = returnOperation(y, "+=")
    operator fun minus(y: DPVariable) = returnOperation(y, "-=")
    operator fun div(y: DPVariable) = returnOperation(y, "/=")
    operator fun rem(y: DPVariable) = returnOperation(y, "%=")

    infix fun storeAndDestroy(id: String) : DPVariable {
        val new = copy(id)
        this.destroy()
        return new
    }

    infix fun copy(id: String) : DPVariable {
        val new = DPVariable(mappings, function, id)
        new set this
        return new
    }

    private fun returnOperation(y: DPVariable, operand: String) : DPVariable {
        // Create a variable to store this value in
        val beforeApply = DPVariable(mappings, function, "temp_before")
        function += beforeApply.createString()
        // Store this value in that variable
        beforeApply set this
        // Do the operation
        function += varOperation(global, id, operand, id2 = y.id)
        // Move that operational change to another variable
        val afterApply = DPVariable(mappings, function, "temp_after")
        afterApply set this
        // Restore this value
        this set beforeApply
        return afterApply
    }

    private fun varOperation(target: String, id: String, operation: String, target2: String = target, id2: String = id) =
        mappings["variable.operation"]!!
            .replace("{target1}", target)
            .replace("{target2}", target2)
            .replace("{id1}", id)
            .replace("{id2}", id2)
            .replace("{operation}", operation)
}