import com.mattmx.datapack.DataPackTranslator
import com.mattmx.datapack.mappings.DataPackMappings
import com.mattmx.datapack.variables.loop.schedule

fun main() {
    val translator = DataPackTranslator("testing", DataPackMappings.TESTING)

    translator {
        builder += "tellraw @a {\"text\":\"loaded!\"}"

        repeat(4, schedule(1, 's')) {
            "tellraw @a {\"text\":\"Hello, world\"}"
        }
    }

    translator.build()
}