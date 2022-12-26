package com.mattmx.datapack.objects

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.enums.RenderType
import com.mattmx.datapack.enums.ScoreboardDisplay
import com.mattmx.datapack.util.global
import net.kyori.adventure.text.Component
import java.util.*

class DPVariable(
    var function: FunctionBuilder,
    val id: String,
    val owner: String = global,
    val type: String = "dummy",
    initial: Int? = null
) {
    private var value: Any? = initial
    private val mappings = function.mappings

    fun update(function: FunctionBuilder) {
        this.function = function
    }

    fun component() = Component.score(this.owner, this.id)

    fun createString() =
        mappings["variable.create"]!!
            .replace("{id}", id)
            .replace("{type}", type)+
                if (value != null)
                    "\n" + mappings["variable.assign"]!!
                        .replace("{target}", owner)
                        .replace("{id}", id)
                        .replace("{value}", value.toString())
                else ""

    fun setString(value: Any) =
        mappings["variable.assign"]!!
            .replace("{target}", owner)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun destroyString() =
        mappings["variable.destroy"]!!
            .replace("{id}", id)


    fun addString(value: Int) =
        mappings["variable.add"]!!
            .replace("{target}", owner)
            .replace("{id}", id)
            .replace("{value}", value.toString())

    fun minusString(value: Int) =
        mappings["variable.remove"]!!
            .replace("{target}", owner)
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

    fun create() : DPVariable {
        function += createString()
        return this
    }

    infix fun take(value: Int) {
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
            .replace("{target1}", owner)
            .replace("{target2}", value.owner)
            .replace("{id1}", id)
            .replace("{id2}", value.id)
            .replace("{operation}", "><")
    }

    fun reset() : DPVariable {
        destroy()
        create()
        return this
    }

    infix fun set(value: Any) {
        function += setString(value)
    }

    infix fun set(value: DPVariable) {
        function += varOperation(owner, id, "=", value.owner, value.id)
    }

    operator fun plusAssign(value: Int) = add(value)
    operator fun plusAssign(value: DPVariable) {
        function += varOperation(owner, id, "+=", value.owner, id2 = value.id)
    }

    operator fun minusAssign(value: Int) = take(value)
    operator fun minusAssign(value: DPVariable) {
        function += varOperation(owner, id, "-=", value.owner, id2 = value.id)
    }

    operator fun divAssign(value: DPVariable) {
        function += varOperation(owner, id, "/=", value.owner, id2 = value.id)
    }

    operator fun timesAssign(value: DPVariable) {
        function += varOperation(owner, id, "*=", value.owner, id2 = value.id)
    }

    operator fun remAssign(value: DPVariable) {
        function += varOperation(owner, id, "%=", value.owner, id2 = value.id)
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
    operator fun times(y: Int) = returnOperation(y, "*=")
    operator fun plus(y: DPVariable) = returnOperation(y, "+=")
    operator fun plus(y: Int) = returnOperation(y, "+=")
    operator fun minus(y: DPVariable) = returnOperation(y, "-=")
    operator fun minus(y: Int) = returnOperation(y, "-=")
    operator fun div(y: DPVariable) = returnOperation(y, "/=")
    operator fun div(y: Int) = returnOperation(y, "/=")
    operator fun rem(y: DPVariable) = returnOperation(y, "%=")
    operator fun rem(y: Int) = returnOperation(y, "%=")

    infix fun storeSafely(id: String): DPVariable {
        val new = copy(id)
        this.destroy()
        return new
    }

    infix fun copy(id: String): DPVariable {
        val new = DPVariable(function, id).create()
        new set this
        return new
    }

    private fun returnOperation(y: Int, operand: String) : DPVariable {
        val temp = DPVariable(function, "unnamed_var_temp", initial = y)
        val returnVal = returnOperation(temp, operand)
        temp.destroy()
        return returnVal
    }

    private fun returnOperation(y: DPVariable, operand: String): DPVariable {
        // Create a variable to store this value in
        val temp = DPVariable(function, "temp_${UUID.randomUUID()}")
        // Store this value in that variable
        temp set this
        temp.varOperation(global, id, operand, id2 = y.id)
        return temp
    }

    private fun varOperation(
        target: String,
        id: String,
        operation: String,
        target2: String = target,
        id2: String = id
    ) =
        mappings["variable.operation"]!!
            .replace("{target1}", target)
            .replace("{target2}", target2)
            .replace("{id1}", id)
            .replace("{id2}", id2)
            .replace("{operation}", operation)
}