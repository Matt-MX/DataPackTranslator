import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.enums.EffectAction
import com.mattmx.datapack.enums.TitleType
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.color
import com.mattmx.datapack.variables.executes.block
import com.mattmx.datapack.variables.executes.location
import com.mattmx.datapack.variables.executes.selector.*

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator["main"] = {
        runOnLoad = true

        val x = variable("x", 5)
        val y = variable("y", 2)
        val z = x * y storeAndDestroy "z"

        call("notifyloaded")
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
                        effect(EffectAction.GIVE, selected(), "speed", 1, 5, true)
                        title(selected(), "".color(), "&bRun!".color())
                    }
                }
                execIf(blockBelow eq "emerald_block") {
                    run {
                        effect(EffectAction.GIVE, selected(), "jump_boost", 1, 5, true)
                        title(selected(), "".color(), "&aJump!".color())
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