package com.mattmx.datapack

import com.google.gson.GsonBuilder
import com.mattmx.datapack.event.Event
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.objects.McMetaFile
import com.mattmx.datapack.util.EntryType
import com.mattmx.datapack.objects.datafiles.DPFormatConfig
import com.mattmx.datapack.objects.datafiles.TickLoadFile
import com.mattmx.datapack.util.funcNextRef
import java.io.File

class DataPackTranslator(val id: String, val mappings: DataPackMappings) {
    val functions = hashMapOf<String, FunctionBuilder>()
    val listenersInitFunc = FunctionBuilder(this)
    val listenerFunc = FunctionBuilder(this)
    val tempVarCleanup = FunctionBuilder(this)
    val meta = McMetaFile(id)
    val config = DPFormatConfig()
    private val load = TickLoadFile(id)
    private val tick = TickLoadFile(id)

    operator fun set(fileName: String, value: FunctionBuilder) {
        functions[fileName] = value
        if (value.runOnLoad) load(fileName)
        if (value.runOnTick) tick(fileName)
    }

    inline operator fun set(fileName: String, value: FunctionBuilder.() -> Unit) {
        val func = FunctionBuilder(this)
        set(fileName, func)
        value(func)
        if (func.runOnLoad) load(fileName)
        if (func.runOnTick) tick(fileName)
    }

    inline operator fun invoke(fileName: String, value: FunctionBuilder.() -> Unit) = set(fileName, value)

    operator fun invoke(fileName: String, value: FunctionBuilder) = set(fileName, value)

    operator fun get(builder: FunctionBuilder) = functions.entries.first { it.value == builder }.key

    fun load(id: String): DataPackTranslator {
        load.values += "${this.id}:$id"
        return this
    }

    fun tick(id: String): DataPackTranslator {
        tick.values += "${this.id}:$id"
        return this
    }

    inline fun listener(event: Event, callback: FunctionBuilder.() -> Unit) {
        val callbackFunc = FunctionBuilder(this)
        callback(callbackFunc)
        event.callback = callbackFunc
        event.build(this, listenersInitFunc, listenerFunc)
    }

    fun build() {
        val gson = GsonBuilder().setPrettyPrinting().create()

        listenersInitFunc.runOnLoad = true
        listenerFunc.runOnTick = true
        this["listeners_init"] = listenersInitFunc
        this["listeners_run"] = listenerFunc
        this["temp_var_cleanup"] = tempVarCleanup

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
            functions.forEach { (name, function) ->
                // Get all the split content per function
                val splitContent = function.build()
                splitContent.forEachIndexed { index, it ->
                    val funcName = if (index == 0) name else "${name}_${index}"
                    val content = it.replace(funcNextRef, "${index + 1}")
                    val file = File("$funcFile/$funcName.mcfunction")
                    file.parentFile.mkdirs()
                    file.createNewFile()
                    file.writeText(
                        "###########################################\n"
                                + "#         COMPILED BY MATTMX'S            #\n"
                                + "#       Kotlin DataPack Translator        #\n"
                                + "###########################################\n"
                                + content
                    )
                }
            }
        }

        // Now we can apply modules
        if (config.modules.isNotEmpty()) {
            config.modules.forEach {
                // Write all files
                it.all().forEach { (fileLocation, fileContent) ->
                    val file = File("$dataFile/${it.name}/functions/$fileLocation")
                    file.parentFile.mkdirs()
                    file.createNewFile()
                    file.writeText(fileContent)
                }
                // Add entry points
                it.entryPoints.forEach { entry ->
                    if (entry.type == EntryType.LOAD) {
                        load.values += "${it.name}:${entry.name}"
                    } else tick.values += "${it.name}:${entry.name}"
                }
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