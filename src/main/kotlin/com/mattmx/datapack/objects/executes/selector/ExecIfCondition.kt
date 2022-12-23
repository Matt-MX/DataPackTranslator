package com.mattmx.datapack.objects.executes.selector

import com.mattmx.datapack.util.global

open class ExecIfCondition<T : Any>(val name: String) {
    var conditions = arrayListOf<String>()

    infix fun and(condition: ExecIfCondition<*>) : ExecIfCondition<T> {
        conditions += condition.conditions
        return this
    }

    protected open fun condition(condition: String) : String = "$name=$condition"

    operator fun plus(condition: ExecIfCondition<*>) = and(condition)

    fun build() = conditions.joinToString(" if ")

    override fun toString() = build()
}

class IfScoreCondition(val scoreName: String, val target: String) : ExecIfCondition<Any>("score") {
    override fun condition(condition: String) = "$name $target $scoreName $condition"

    infix fun lt(other: IfScoreCondition) : IfScoreCondition {
        conditions += condition("< ${other.target} ${other.scoreName}")
        return this
    }

    infix fun lt(score: Int) : IfScoreCondition {
        conditions += condition("matches .$score")
        return this
    }

    infix fun lte(other: IfScoreCondition) : IfScoreCondition {
        conditions += condition("<= ${other.target} ${other.scoreName}")
        return this
    }

    infix fun lte(score: Int) : IfScoreCondition {
        conditions += condition("matches ..$score")
        return this
    }

    infix fun gt(other: IfScoreCondition) : IfScoreCondition {
        conditions += condition("> ${other.target} ${other.scoreName}")
        return this
    }

    infix fun gt(score: Int) : IfScoreCondition {
        conditions += condition("matches $score.")
        return this
    }

    infix fun gte(other: IfScoreCondition) : IfScoreCondition {
        conditions += condition(">= ${other.target} ${other.scoreName}")
        return this
    }

    infix fun gte(score: Int) : IfScoreCondition {
        conditions += condition("matches $score..")
        return this
    }

    infix fun eq(other: IfScoreCondition) : IfScoreCondition {
        conditions += condition("= ${other.target} ${other.scoreName}")
        return this
    }

    infix fun eq(score: Int) : IfScoreCondition {
        conditions += condition("matches $score")
        return this
    }
}

fun score(name: String, target: String = global) = IfScoreCondition(name, target)
fun IfScoreCondition.score(name: String, target: String) = "$name $target"