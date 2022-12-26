import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.builders.ItemBuilder
import com.mattmx.datapack.builders.item
import com.mattmx.datapack.enums.EffectAction
import com.mattmx.datapack.event.BlockMined
import com.mattmx.datapack.event.ItemUsed
import com.mattmx.datapack.gui.DPInventory
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
import com.mattmx.datapack.util.raycast.raycast

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator.config {
        // Adds module math, math functions like sqrt can now be called.
        modules += Module.MATH
        executeBlockStoredInFunction = true
    }

    translator("raycast") {
        val raycastResult = raycast(selected(), 200, 0.1, true) storeSafely "raycast_result"
        tellraw(allPlayers(), "&cRaycast result: ".color() + raycastResult.component())
    }

    translator.listener(ItemUsed("carrot_on_stick_used", "carrot_on_a_stick")) {
        tellraw(allPlayers(), "&6Carrot on stick was used!".color())
        this += "give @a " +
                item("carrot_on_a_stick") {
                    name = "&a&l[CLICK]".color()
                    lore {
                        this += "&7Lore line one".color()
                        this += "&eLore line two".color()
                    }
                    enchantments {
                        this["minecraft:sharpness"] = 20
                    }
                }
    }

    val testInv = DPInventory(translator, "test")
    testInv.setItem(0, item("diamond_sword") {
        name = "&r&cTest item".color()
    }.toString())
    testInv.create()

    translator("testgui") {
        execAs(selected()) {
            call("gui/test/init")
        }
    }

    translator("vars") {
        val x = variable("x", default = 9).reset()
        val y = variable("y", default = 2).reset()

        // Generate a random number between 0, 100 and store it in a var called "result"
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

    translator("countdown") {
        repeat(6, 1.seconds()) {
            val timeLeft = this.counterVariable()
            title(allPlayers(), "&6Countdown".color(), "&eDone in ".color() + timeLeft.component() + "s".color())
        }
        // Below is another file
        title(allPlayers(), "".color(), "&eCountdown complete".color())
    }

    translator("main") {
        runOnLoad = true

        call("notifyloaded")
    }

    translator("notifyloaded") {
        tellraw(allPlayers(), "&7[&cDataPack&7] Loaded!".color())
    }

    translator("runeffects") {
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