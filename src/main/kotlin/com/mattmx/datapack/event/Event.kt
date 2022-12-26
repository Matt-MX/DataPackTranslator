package com.mattmx.datapack.event

import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.FunctionBuilder
import com.mattmx.datapack.objects.DPVariable
import com.mattmx.datapack.objects.executes.selector.allEntities
import com.mattmx.datapack.objects.executes.selector.allPlayers
import com.mattmx.datapack.objects.executes.selector.escore

open class Event(
    val eventName: String,
    val eventType: String
) {
    var callback: FunctionBuilder? = null

    fun build(
        translator: DataPackTranslator,
        loadEventsFunction: FunctionBuilder,
        listenerFunction: FunctionBuilder
    ) {
        // Create the variable for the event tracking
        loadEventsFunction.variable(eventName, eventType).create()
        // Write to the listener function
        listenerFunction.execAs(allEntities() where (escore(eventName) gte 1)) {
            run {
                // Call the function
                call("events/$eventName")
                // Reset this player's score
                this += "scoreboard players reset @s $eventName"
            }
        }.build()
        // Finally create the listener callback
        translator("events/$eventName", callback!!)
    }

}

class TriggerEvent(objName: String) : Event(objName, "trigger")
class PlayerTotalKillEvent(objName: String) : Event(objName, "totalKillCount")
class PlayerKillEvent(objName: String) : Event(objName, "playerKillCount")
class HealthEvent(objName: String) : Event(objName, "health")
class DeathCountEvent(objName: String) : Event(objName, "deathCount")
class XPEvent(objName: String) : Event(objName, "xp")
class LevelEvent(objName: String) : Event(objName, "level")
class FoodEvent(objName: String) : Event(objName, "food")
class AirEvent(objName: String) : Event(objName, "air")
class ArmorEvent(objName: String) : Event(objName, "armor")
class KilledByTeam(objName: String, team: String) : Event(objName, "killedByTeam.$team")
class TeamKillEvent(objName: String, team: String) : Event(objName, "teamkill.$team")
class BlockBroken(objName: String, blockType: String) : Event(objName, "minecraft.broken:minecraft.${blockType.lowercase()}")
class CraftedItem(objName: String, itemType: String) : Event(objName, "minecraft.crafted:minecraft.${itemType.lowercase()}")
class ItemDropped(objName: String, itemType: String) : Event(objName, "minecraft.dropped:minecraft.${itemType.lowercase()}")
class KillEvent(objName: String, mobType: String) : Event(objName, "minecraft.killed:minecraft.${mobType.lowercase()}")
class DeathEvent(objName: String, mobType: String) : Event(objName, "minecraft.killed_by:minecraft.${mobType.lowercase()}")
class BlockMined(objName: String, blockType: String) : Event(objName, "minecraft.mined:minecraft.${blockType.lowercase()}")
class ItemPickedUp(objName: String, itemType: String) : Event(objName, "minecraft.picked_up:minecraft.${itemType.lowercase()}")
class ItemUsed(objName: String, itemType: String) : Event(objName, "minecraft.used:minecraft.${itemType.lowercase()}")
