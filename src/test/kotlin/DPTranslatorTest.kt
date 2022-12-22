import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.functionBuilder
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.color
import com.mattmx.datapack.util.colorJson
import com.mattmx.datapack.variables.loop.seconds

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator["main"] = {
        runOnLoad = true

        call("notifyLoaded")

        val x = variable("x")
        repeat(10) {
            x.function = this
            x += 10
            // todo be able to cancel loop (use execute ting)
        }

        repeat {

        }
    }

    translator["notifyLoaded"] = {
        tellraw("@a", "&7[&cDataPack&7] Loaded!")
    }

    translator.build()
}