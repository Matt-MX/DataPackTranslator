package com.mattmx.datapack.variables

class Block(val location: Location) {

    infix fun eq(type: String) = "block $location minecraft:$type"

}