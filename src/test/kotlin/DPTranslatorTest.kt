import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.enums.EffectAction
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.color
import com.mattmx.datapack.variables.DPVariable
import com.mattmx.datapack.variables.executes.block
import com.mattmx.datapack.variables.executes.location
import com.mattmx.datapack.variables.executes.selector.*
import com.mattmx.datapack.variables.loop.seconds

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    lateinit var usedItemVariable: DPVariable

    translator["vars"] = {

        val x = variable("x")
        val y = variable("y")

        // Since kotlin doesn't have an override for assignment, we'll use an infix function
        // to store the result safely into a variable called "z", that is then returned.
        val z = x * y storeSafely "z"

    }

    translator["main"] = {
        runOnLoad = true

        usedItemVariable = variable("use_carrot_stick", "minecraft.used:minecraft.carrot_on_a_stick")
        usedItemVariable.reset()

        call("notifyloaded")
    }

    translator["carrot_on_stick"] = {
        runOnTick = true

        execAs(allPlayers() where (score(usedItemVariable) gte 1)) {
            execAt {
                run {
                    tellraw(selected(), "&eYou used it!".color())
                    // Set a variable for this player
                    repeat(5, 1.seconds()) {
                        teleport(allPlayers() where (score(usedItemVariable) gte 1), location(y = "~2"))
                    }
                    // Only reset if variable is not set
                    this += "scoreboard players reset @s ${usedItemVariable.id}"
                }
            }
        }.build()
    }

    translator["notifyloaded"] = {
        tellraw(allPlayers(), "&7[&cDataPack&7] Loaded!".color())
    }

    translator["runeffects"] = {
        runOnTick = true
        execAs(allPlayers()) {
            val blockBelow = block(location() - location(y = "1"))
            execAt {
                execIf(blockBelow eq "amethyst_block") {
                    run {
                        effect(EffectAction.GIVE, selected(), "speed", 1, 7, true)
                    }
                }
                execIf(blockBelow eq "emerald_block") {
                    run {
                        effect(EffectAction.GIVE, selected(), "jump_boost", 1, 5, true)
                    }
                }
                execIf(blockBelow eq "redstone_block") {
                    run {
                        effect(EffectAction.GIVE, selected(), "regeneration", 3, 1, true)
                    }
                }
                execIf(block(location()) eq "tall_grass") {
                    run {
                        effect(EffectAction.GIVE, selected(), "slowness", 1, 2, true)
                        effect(EffectAction.GIVE, selected(), "blindness", 1, 1, true)
                        effect(EffectAction.GIVE, selected(), "jump_boost", 1, 250, true)
                    }
                }
            }
        }.build()
    }

    translator.build()
}