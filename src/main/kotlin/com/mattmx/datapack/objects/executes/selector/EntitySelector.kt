package com.mattmx.datapack.objects.executes.selector

class EntitySelector(val initial: String) : Selector {
    var condition: EntityCondition<*>? = null

    override fun build() =
        if (initial.matches("^@[aserp]\$".toRegex())) "$initial[" + (condition?.build() ?: "") + "]" else initial

    override fun toString() = build()

    infix fun where(condition: EntityCondition<*>) : EntitySelector {
        if (this.condition == null) {
            this.condition = condition
        } else this.condition = this.condition!! and condition
        return this
    }

    operator fun plus(condition: EntityCondition<*>) = where(condition)

}

fun allPlayers() = EntitySelector("@a")
fun selected() = EntitySelector("@s")
fun allEntities() = EntitySelector("@e")
fun player(name: String) = EntitySelector(name)
fun randomPlayer() = EntitySelector("@r")
fun nearestPlayer() = EntitySelector("@p")