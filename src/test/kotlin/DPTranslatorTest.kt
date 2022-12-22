import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.color
import com.mattmx.datapack.variables.executes.block
import com.mattmx.datapack.variables.executes.location

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator["main"] = {
        runOnLoad = true
        val testlist = list("arrayTest")

        testlist[0] = -10000
        testlist[0] -= 100

        repeat {
            tellraw("@a", "&cLooping".color())
        }

        execIf(block(location()) eq "air", block(location(y = "~1")) eq "air") {
            execUnless(block(location() + location(y = "-1")) eq "air") {
                run {
                    repeat(3) {
                        tellraw("@a", "&cYou're in air".color())
                    }
                }
            }
        }.build()
    }

    translator["notifyLoaded"] = {
        tellraw("@a", "&7[&cDataPack&7] Loaded!".color())
    }

    translator.build()
}