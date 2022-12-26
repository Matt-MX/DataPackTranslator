package com.mattmx.datapack.objects.executes.selector

import com.mattmx.datapack.objects.Location

class ExecPositionedCondition(
    val dx: Double,
    val dy: Double,
    val dz: Double
) : Location(dx.toString(), dy.toString(),dz.toString()) {

    override fun toString() = "^$dx ^$dy ^$dz"

}

fun position(dx: Double = 0.0, dy: Double = 0.0, dz: Double = 0.0) : ExecPositionedCondition =
    ExecPositionedCondition(dx, dy, dz)