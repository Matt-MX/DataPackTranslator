package com.mattmx.datapack

import com.google.gson.GsonBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.objects.McMetaFile
import com.mattmx.datapack.objects.datafiles.TickLoadFile
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.loop.DPForLoop
import com.mattmx.datapack.variables.loop.ScheduleTime
import com.mattmx.datapack.variables.loop.schedule
import java.io.File

class DataPackTranslator(val id: String, val mappings: DataPackMappings) {
    // todo allow array of files here
    val builder = DataPackBuilder()
    val functions = hashMapOf<String, String>()
    val meta = McMetaFile(id)
    val load = TickLoadFile(id)
    val tick = TickLoadFile(id)

    operator fun invoke(builder: DataPackTranslator.() -> Unit) {
        // todo run builder and save to list of shit to do
        builder.invoke(this)
    }

    fun createVar(id: String, default: Any? = null) {
        builder += DPVariable(mappings, id, default).create()
    }

    fun setVar(id: String, value: Any) {
        builder += DPVariable(mappings, id).set(value)
    }

    fun removeVar(id: String) {
        builder += DPVariable(mappings, id).remove()
    }

    fun addVar(id: String, value: Int) {
        builder += DPVariable(mappings, id).add(value)
    }

    fun tellraw(target: String, value: String) {
        builder += com.mattmx.datapack.commands.tellraw(mappings, target, value)
    }

    fun repeat(times: Int, period: ScheduleTime = schedule(1, 't'), str: () -> String) {
        val ret = DPForLoop(this, str, times, period).build()
        builder += ret.third
        functions[ret.first] = ret.second
    }

    fun build() {
        val gson = GsonBuilder().setPrettyPrinting().create()

        // Create directory
        val topFile = File("./$id/")
        if (topFile.exists())
            topFile.deleteRecursively()
        topFile.mkdir()

        // Create meta file
        val metaFile = File("$topFile/pack.mcmeta")
        metaFile.createNewFile()
        metaFile.writeText(gson.toJson(meta))

        // Create data folder
        val dataFile = File("$topFile/data/")
        dataFile.mkdir()

        // Create function folder
        val funcFile = File("$dataFile/$id/functions/")
        funcFile.mkdirs()

        // Write all functions
        if (functions.isNotEmpty()) {
            functions.forEach { name, content ->
                val file = File("$funcFile/$name.mcfunction")
                file.createNewFile()
                file.writeText(content)
            }
        }
        // Create main file
        val file = File("$funcFile/main.mcfunction")
        file.createNewFile()
        file.writeText(builder.toString())

        val tickFunc = File("$funcFile/tick.mcfunction")
        tickFunc.createNewFile()

        load.values += "$id:main"
        tick.values += "$id:tick"

        // Create load.json and tick.json
        val tagFunctions = File("$dataFile/minecraft/tags/functions/")
        tagFunctions.mkdirs()

        val loadJsonFile = File("$tagFunctions/load.json")
        loadJsonFile.createNewFile()
        loadJsonFile.writeText(gson.toJson(load))

        val tickJsonFile = File("$tagFunctions/tick.json")
        tickJsonFile.createNewFile()
        tickJsonFile.writeText(gson.toJson(tick))

    }

    override fun toString() = builder.toString()

    class DataPackBuilder {
        private val builder = arrayListOf<String>()
        operator fun plusAssign(line: String) {
            builder += line
        }

        override fun toString(): String = builder.joinToString("\n")
    }
}