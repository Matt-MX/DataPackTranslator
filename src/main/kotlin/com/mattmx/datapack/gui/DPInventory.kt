package com.mattmx.datapack.gui

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.objects.executes.selector.EntitySelector

class DPInventory(val translator: DataPackTranslator, val name: String) {
    private val initFunc = FunctionBuilder(translator)
    private val type = "entity @e[type=minecraft:chest_minecart,tag=gui_$name]"
    private val items = hashMapOf<Int, String>()

    init {
        translator("gui/$name/init", initFunc)
    }

    fun create() {
        // Summon or set block
        initFunc += "summon minecraft:chest_minecart ~ ~ ~ {Tags:[\"gui_$name\"]}"
        // Set the items
        items.forEach { (slot, item) ->
            initFunc += "item replace $type container.$slot with $item"
        }
    }

    fun open(entity: EntitySelector) {
        // Open for that specific Entity
    }

    fun setItem(slot: Int, item: String) {
        items += slot to item
    }

}