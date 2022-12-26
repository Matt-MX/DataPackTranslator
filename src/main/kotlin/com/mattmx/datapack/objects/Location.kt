package com.mattmx.datapack.objects

open class Location(
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

    operator fun minus(other: Location) : Location {
        x = (if ("~" in x) "~" else "") + ((x - "~").toInt(0) - (other.x - "~").toInt(0))
        y = (if ("~" in y) "~" else "") + ((y - "~").toInt(0) - (other.y - "~").toInt(0))
        z = (if ("~" in z) "~" else "") + ((z - "~").toInt(0) - (other.z - "~").toInt(0))
        return this
    }
}

private operator fun String.minus(string: String) = this.replace(string, "")
private fun String.toInt(default: Int) = toIntOrNull() ?: default