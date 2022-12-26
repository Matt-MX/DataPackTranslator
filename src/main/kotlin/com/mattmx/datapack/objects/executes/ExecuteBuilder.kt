package com.mattmx.datapack.objects.executes

import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.objects.Block
import com.mattmx.datapack.objects.Location
import com.mattmx.datapack.objects.executes.selector.EntitySelector
import com.mattmx.datapack.objects.executes.selector.ExecPositionedCondition
import com.mattmx.datapack.objects.executes.selector.selected

class ExecuteBuilder(val function: FunctionBuilder, val parent: ExecuteBuilder? = null) {
    val children = linkedMapOf<ExecuteCondition, ExecuteBuilder>()

    fun conditionIf(value: String) = createCondition(IfCondition(value))
    fun conditionUnless(value: String) = createCondition(UnlessCondition(value))
    fun conditionAs(value: EntitySelector) = createCondition(AsCondition(value))
    fun conditionAt(value: EntitySelector = selected()) = createCondition(AtCondition(value.toString()))
    fun conditionStore(action: String) = createCondition(StoreCondition(action))
    fun conditionPositioned(value: ExecPositionedCondition) = createCondition(PositionedCondition(value.toString()))

    private fun createCondition(condition: ExecuteCondition): ExecuteBuilder {
        val next = ExecuteBuilder(function, this)
        children[condition] = next
        return next
    }

    inline fun run(value: FunctionBuilder.() -> Unit): ExecuteBuilder {
        val funcBuilder = FunctionBuilder(function.translator)
        value(funcBuilder)
        val builder = ExecuteBuilder(function, this)
        funcBuilder.lines().forEach {
            this.children[RunCondition(it)] = builder
        }
        return root()
    }

//    fun execElse(builder: ExecuteBuilder.() -> Unit) : ExecuteBuilder {
//        // Get parent condition
//        if (parent == null) throw Error("Parent cannot be null using execElse.")
//        val parentValue = parent.children.map { it.toPair() }.firstOrNull { it.second == this }
//            ?: throw Error("Parent condition cannot be null using execElse.")
//        return execUnless(parentValue.first.value, builder = builder)
//    }

    fun build() {
        this.function.builder += this.root().toString()
    }

    fun execIf(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionIf(conditions.joinToString(" if ")) }, builder)

    fun execUnless(vararg conditions: String, builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionUnless(conditions.joinToString(" unless ")) }, builder)

    fun execAs(condition: EntitySelector, builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionAs(condition) }, builder)

    fun execAt(condition: EntitySelector = selected(), builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionAt(condition) }, builder)

    fun execStore(action: String, builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionStore(action) }, builder)

    fun positioned(action: ExecPositionedCondition, builder: ExecuteBuilder.() -> Unit) =
        compile({ it.conditionPositioned(action) }, builder)

    inline fun compile(
        condition: (ExecuteBuilder) -> ExecuteBuilder,
        builder: ExecuteBuilder.() -> Unit
    ): ExecuteBuilder {
        val executeBuilder = condition(this)
        builder(executeBuilder)
        return executeBuilder
    }

    fun root(): ExecuteBuilder {
        return parent?.root() ?: this
    }

    fun list() = children.map { (condition, next) -> "${condition.name} ${condition.value}" + next.toString() }

    fun pairs(): List<Pair<ExecuteCondition, ExecuteBuilder>> =
        if (children.isNotEmpty()) children.map { listOf(it.value.pairs()).flatten() + it.toPair() }.flatten()
        else children.map { it.toPair() }

    override fun toString() = buildString().joinToString("\n")

    fun buildString(): List<String> {
        var builder = mutableListOf<String>()
        children.forEach { (condition, next) ->
            if (next.children.isEmpty()) {
                // We've reached the end of the tree
                builder += "${condition.name} ${condition.value}"
            } else builder += next.buildString().map { "${condition.name} ${condition.value} $it" }
        }
        // If we're at the root of the tree we need to add execute
        if (parent == null)
            builder = builder.map { "execute $it" }.toMutableList()
        return builder
    }
}

fun block(location: Location) = Block(location)
fun location(x: Float, y: Float, z: Float) = location(x.toString(), y.toString(), z.toString())
fun location(x: Int, y: Int, z: Int) = location(x.toString(), y.toString(), z.toString())
fun location(x: String? = "~", y: String? = "~", z: String? = "~") = Location(x ?: "~", y ?: "~", z ?: "~")