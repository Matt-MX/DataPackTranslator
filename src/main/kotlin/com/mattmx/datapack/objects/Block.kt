package com.mattmx.datapack.objects

class Block(val location: Location) {

    infix fun eq(type: String) = "block $location minecraft:$type"

}