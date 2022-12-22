package com.mattmx.datapack.mappings

import com.mattmx.datapack.util.SmartError
import java.io.File

class DataPackMappings {
    private val mappings = hashMapOf<String, String>()

    operator fun get(id: String) = mappings[id]

    companion object {

        val TESTING = fromFile(File("testMappings.txt"))

        fun fromFile(file: File) : DataPackMappings {
            val lines = file.readLines()
            val mappings = DataPackMappings()
            var lineNum = 0
            lines.forEach {
                if (!it.startsWith("#")) {
                    val split = it.split(":\\s".toRegex())
                    if (split.size <= 1)
                        throw SmartError("Couldn't parse this line, if you want to comment start the line with #!", it, lineNum)
                    val linesCombined = split.subList(1, split.size).joinToString(": ")
                    mappings.mappings[split[0]] = linesCombined
                }
                lineNum++
            }
            return mappings
        }
    }
}