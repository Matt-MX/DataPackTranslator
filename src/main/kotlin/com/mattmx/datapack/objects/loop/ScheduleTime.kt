package com.mattmx.datapack.objects.loop

class ScheduleTime(val amount: Int, val unit: Char) {
    override fun toString() = "$amount$unit"
}
fun Int.ticks() = ScheduleTime(this, 't')
fun Int.seconds() = ScheduleTime(this, 's')
fun Int.minutes() = ScheduleTime(this, 'm')