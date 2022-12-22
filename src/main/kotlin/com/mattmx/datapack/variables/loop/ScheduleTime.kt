package com.mattmx.datapack.variables.loop

class ScheduleTime(val amount: Int, val unit: Char) {
    override fun toString() = "$amount$unit"
}

fun schedule(amount: Int, unit: Char) = ScheduleTime(amount, unit)