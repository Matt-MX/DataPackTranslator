import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.enums.EffectAction
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.*
import com.mattmx.datapack.objects.DPVariable
import com.mattmx.datapack.objects.executes.block
import com.mattmx.datapack.objects.executes.location
import com.mattmx.datapack.objects.executes.selector.*
import com.mattmx.datapack.objects.functions.FunctionWrapper
import com.mattmx.datapack.objects.functions.random
import com.mattmx.datapack.objects.functions.sqrt
import com.mattmx.datapack.objects.loop.seconds

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator.config {
        // Adds module math, math functions like sqrt can now be called.
        modules += Module.MATH
        executeBlockStoredInFunction = true
    }

    lateinit var usedItemVariable: DPVariable

    translator["vars"] = {
        val x = variable("x", default = 9).reset()
        val y = variable("y", default = 2).reset()

        val result = random(0, 100) copy "result"
        tellraw(allPlayers(), "&7Random number: ".color() + result.component())

        tellraw(allPlayers(), "&7x = ".color() + x.component())
        tellraw(allPlayers(), "&7y = ".color() + y.component())

        // Since kotlin doesn't have an override for assignment, we'll use an infix function
        // to store the result safely into a variable called "z", that is then returned.
        val z = x * x * y storeSafely "z"

        tellraw(allPlayers(), "&7z = x * y (=".color() + z.component() + ")".color())

        execIf(score("x") gte 10) {
            run {
                tellraw(allPlayers() + (name() eq "MattMX"), "&dHi, matt, x >= 10".color())
            }
        }.build()

    }

    translator["countdown"] = {
        var opposite = variable("opposite", default = 5)
        repeat(6, 1.seconds()) {
            opposite.update(this)
            title(allPlayers(), "&6Countdown".color(), "&eDone in ".color() + opposite.component() + "s".color())
            opposite--
        }
    }

    translator["main"] = {
        runOnLoad = true

        usedItemVariable = variable("use_carrot_stick", "minecraft.used:minecraft.carrot_on_a_stick")
        usedItemVariable.reset()

        call("notifyloaded")
    }

    translator["carrot_on_stick"] = {
        runOnTick = true

        execAs(allPlayers() where (escore(usedItemVariable) gte 1)) {
            execAt {
                // todo different run functions should be in different func files for efficiency
                run {
                    tellraw(selected(), "&eYou used it!".color())
                    // Set a variable for this player
                    repeat(5, 1.seconds()) {
                        teleport(allPlayers() where (escore(usedItemVariable) gte 1), location(y = "~2"))
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