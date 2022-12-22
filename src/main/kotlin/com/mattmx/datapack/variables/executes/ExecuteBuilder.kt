package com.mattmx.datapack.variables.executes

import com.mattmx.datapack.objects.Block
import com.mattmx.datapack.objects.Location

class ExecuteBuilder(val parent: ExecuteBuilder? = null) {
    var condition: ExecuteCondition? = null

    fun conditionIf(value: String) : ExecuteBuilder {
        condition = IfCondition(value)
        return ExecuteBuilder(this)
    }

    fun conditionUnless(value: String) : ExecuteBuilder {
        condition = UnlessCondition(value)
        return ExecuteBuilder(this)
    }

    fun conditionAs(value: String) : ExecuteBuilder {
        condition = AsCondition(value)
        return ExecuteBuilder(this)
    }

    fun conditionAt(value: String) : ExecuteBuilder {
        condition = AtCondition(value)
        return ExecuteBuilder(this)
    }

    fun positioned(value: String) : ExecuteBuilder {
        condition = PositionedCondition(value)
        return ExecuteBuilder(this)
    }

    fun run(value: String) : String {
        condition = RunCondition(value)
        return this.toString()
    }

    fun executeIf(value: String, builder: ExecuteBuilder.() -> Unit) : ExecuteBuilder {
        val new = conditionIf(value)
        builder(new)
        return new
    }

    override fun toString(): String {
        var thisString = (condition?.name ?: "") + " " + (condition?.value ?: "")
        thisString = if (parent != null)
            "$parent $thisString"
        else "execute $thisString"
        return thisString
    }
}

fun block(location: Location) = Block(location)
fun location(x: Float, y: Float, z: Float) = location(x.toString(), y.toString(), z.toString())
fun location(x: Int, y: Int, z: Int) = location(x.toString(), y.toString(), z.toString())
fun location(x: String? = "~", y: String? = "~", z: String? = "~") = Location(x ?: "~", y ?: "~", z ?: "~")

fun executeBuilder(builder: ExecuteBuilder.() -> Unit) : ExecuteBuilder {
    val executor = ExecuteBuilder()
    builder(executor)
    return executor
}