package com.mattmx.datapack.builders

import com.mattmx.datapack.util.json
import net.benwoodworth.knbt.*
import net.kyori.adventure.text.Component

class ItemBuilder(var type: String) {
    var name: Component? = null
    var amount = 1
    var damage = 0
    val lore = arrayListOf<Component>()
    val enchanments = hashMapOf<String, Int>()
    val nbt = arrayListOf<NbtCompound.() -> Unit>()

    inline fun lore(builder: ArrayList<Component>.() -> Unit) : ItemBuilder {
        builder(lore)
        return this
    }
    inline fun enchantments(builder: HashMap<String, Int>.() -> Unit) : ItemBuilder {
        builder(this.enchanments)
        return this
    }
    fun nbt(builder: NbtCompound.() -> Unit) : ItemBuilder {
        nbt += builder
        return this
    }

    fun build(): NbtCompound {
        val item = buildNbtCompound {
            put("id", if (type.contains(":")) type else "minecraft:$type")
            put("Count", amount.toByte())
//            put("tag", buildNbtCompound {
                put("display", buildNbtCompound {
                    name?.let {
                        put("Name", it.json())
                    }
                    put("Lore", buildNbtList {
                        lore.forEach { add(it.json()) }
                    })
                })
                put("damage", damage)
                put("Enchantments", buildNbtList {
                    enchanments.forEach { (id, lvl) ->
                        add(buildNbtCompound {
                            put("id", id)
                            put("lvl", lvl.toShort())
                        })
                    }
                })
//            })
        }
        nbt.forEach { it(item) }
        return item
    }

    override fun toString() = (if (type.contains(":")) type else "minecraft:$type") + build()

}

inline fun item(type: String, builder: ItemBuilder.() -> Unit) : ItemBuilder {
    val item = ItemBuilder(type)
    builder(item)
    return item
}