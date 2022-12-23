package com.mattmx.datapack.util

import com.google.gson.GsonBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

private val serializer = LegacyComponentSerializer.builder()
    .character('&')
    .hexCharacter('#')
    .hexColors()
    .build()

private val gson = GsonBuilder().create()
operator fun Component.plus(other: Component) = this.append(other)
fun String.color() = serializer.deserialize(this)
fun String.colorJson() = color().json()

fun Component.json(): String = GsonComponentSerializer.gson().serialize(this)