package com.mattmx.datapack.variables.executes.selector

import net.kyori.adventure.text.NBTComponent

open class EntityCondition<T : Any>(val name: String) {
    private var conditions = arrayListOf<String>()

    infix fun lt(other: T) : EntityCondition<T> {
        conditions += condition(".$other")
        return this
    }

    infix fun lte(other: T) : EntityCondition<T> {
        conditions += condition("..$other")
        return this
    }

    infix fun gt(other: T) : EntityCondition<T> {
        conditions += condition("$other.")
        return this
    }

    infix fun gte(other: Any) : EntityCondition<T> {
        conditions += condition("$other..")
        return this
    }

    infix fun eq(other: Any) : EntityCondition<T> {
        conditions += condition(other.toString())
        return this
    }

    infix fun and(condition: EntityCondition<*>) : EntityCondition<T> {
        conditions += condition.conditions
        return this
    }

    protected open fun condition(condition: String) : String = "$name=$condition"

    operator fun plus(condition: EntityCondition<*>) = and(condition)

    fun build() = conditions.joinToString(",")
}

class ScoreCondition(val scoreName: String) : EntityCondition<Int>("scores") {
    override fun condition(condition: String) = "$name={scoreName=$condition}"
}

enum class GameMode {
    SURVIVAL,
    ADVENTURE,
    SPECTATOR,
    CREATIVE
}

enum class Sort {
    ARBITRARY,
    FURTHEST,
    NEAREST,
    RANDOM
}

fun score(name: String) = ScoreCondition(name)
fun x() = EntityCondition<Double>("x")
fun y() = EntityCondition<Double>("y")
fun z() = EntityCondition<Double>("z")
fun xrotation() = EntityCondition<Double>("x_rotation")
fun yrotation() = EntityCondition<Double>("y_rotation")
fun zrotation() = EntityCondition<Double>("z_rotation")
fun advancements() = EntityCondition<String>("advancements")
fun distance() = EntityCondition<Double>("distance")
fun dx() = EntityCondition<Double>("dx")
fun dy() = EntityCondition<Double>("dy")
fun dz() = EntityCondition<Double>("dz")
fun gamemode() = EntityCondition<GameMode>("gamemode")
fun level() = EntityCondition<Int>("level")
fun limit() = EntityCondition<Int>("limit")
fun name() = EntityCondition<String>("name")
fun nbt() = EntityCondition<String>("nbt")
fun predicate() = EntityCondition<String>("predicate")
fun sort() = EntityCondition<Sort>("sort")
fun tag() = EntityCondition<String>("tag")
fun team() = EntityCondition<String>("team")
fun type() = EntityCondition<String>("type")