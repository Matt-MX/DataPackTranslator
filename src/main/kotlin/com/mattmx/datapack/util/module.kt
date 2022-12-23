package com.mattmx.datapack.util

import java.io.File

class Module(val name: String, val entryPoints: List<ModuleEntryPoint>, private val files: HashMap<String, String>) {
    fun all() = files

    companion object {
        val MATH = module("nnmath", entryPoint("setup")) {
            val mathFile = File("./modules/math/")
            val files = mathFile.walk().filter { it.name.endsWith(".mcfunction") }
            files.map { it.path }.toList()
        }
    }
}

class ModuleEntryPoint(val name: String, val type: EntryType = EntryType.LOAD) {
    operator fun plus(other: ModuleEntryPoint) = listOf(this, other)
}

fun entryPoint(name: String, type: EntryType = EntryType.LOAD) = listOf(ModuleEntryPoint(name, type))

enum class EntryType {
    LOAD,
    TICK
}

fun module(namespace: String, entryPoints: List<ModuleEntryPoint>, files: () -> List<String>) : Module =
    module(namespace, entryPoints, *files().toTypedArray())

fun module(namespace: String, entryPoints: List<ModuleEntryPoint>, vararg files: String) : Module {
    val fileMap = hashMapOf<String, String>()
    files.forEach {
        val file = File(it)
        if (!file.exists()) throw Error("File $file not found!")
        fileMap[file.path.replace("\\.\\\\modules\\\\\\w+\\\\".toRegex(), "")] = file.readText()
    }
    return Module(namespace, entryPoints, fileMap)
}