package com.mattmx.datapack

import com.google.gson.GsonBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.objects.McMetaFile
import com.mattmx.datapack.objects.datafiles.DPFormatConfig
import com.mattmx.datapack.objects.datafiles.TickLoadFile
import java.io.File

class DataPackTranslator(val id: String, val mappings: DataPackMappings) {
    val functions = hashMapOf<String, FunctionBuilder>()
    val meta = McMetaFile(id)
    val config = DPFormatConfig()
    private val load = TickLoadFile(id)
    private val tick = TickLoadFile(id)

    operator fun set(fileName: String, value: FunctionBuilder) {
        functions[fileName] = value
        if (value.runOnLoad) load(fileName)
        if (value.runOnTick) tick(fileName)
    }

    operator fun set(fileName: String, value: FunctionBuilder.() -> Unit) {
        val func = FunctionBuilder(this)
        value(func)
        set(fileName, func)
    }

    private fun load(id: String): DataPackTranslator {
        load.values += "${this.id}:$id"
        return this
    }

    private fun tick(id: String): DataPackTranslator {
        tick.values += "${this.id}:$id"
        return this
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
            functions.forEach { (name, content) ->
                val file = File("$funcFile/$name.mcfunction")
                file.parentFile.mkdirs()
                file.createNewFile()
                file.writeText(
                    "###########################################\n"
                            + "#         COMPILED BY MATTMX'S            #\n"
                            + "#       Kotlin DataPack Translator        #\n"
                            + "###########################################\n"
                            + content.toString()
                )
            }
        }

        // Create load.json and tick.json
        val tagFunctions = File("$dataFile/minecraft/tags/functions/")
        tagFunctions.mkdirs()

        if (load.values.isEmpty()) {
            load.values += "$id:load"
            File("$funcFile/load.mcfunction").createNewFile()
        }
        val loadJsonFile = File("$tagFunctions/load.json")
        loadJsonFile.createNewFile()
        loadJsonFile.writeText(gson.toJson(load))

        if (tick.values.isEmpty()) {
            tick.values += "$id:tick"
            File("$funcFile/tick.mcfunction").createNewFile()
        }
        val tickJsonFile = File("$tagFunctions/tick.json")
        tickJsonFile.createNewFile()
        tickJsonFile.writeText(gson.toJson(tick))

    }
}