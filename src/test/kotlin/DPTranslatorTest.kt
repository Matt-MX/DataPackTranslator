import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.util.color
import com.mattmx.datapack.util.json
import com.mattmx.datapack.variables.loop.schedule

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator {
        tellraw("@a", "&7[&cTest&7] &fDatapack loaded.")

        createVar("x", 30)
        addVar("x", 30)

        repeat(4, schedule(1, 's')) {
            "tellraw @a ${"&c&lHello, world".color().json()}"
        }
    }

    translator.build()
}