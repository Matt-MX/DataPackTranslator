package com.mattmx.datapack.objects

class Location(
    var x: String = "~",
    var y: String = "~",
    var z: String = "~"
) {
    override fun toString() = "$x $y $z"

    operator fun plus(other: Location) : Location {
        this.x += other.x
        this.y += other.y
        this.z += other.z
        x = x.replace("~~", "~")
        y = y.replace("~~", "~")
        z = z.replace("~~", "~")
        return this
    }
}