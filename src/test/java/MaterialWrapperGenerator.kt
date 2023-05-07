import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

fun main() {
    val mapper = ObjectMapper()
    val jsonBlocks = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("blocks.json"))
    val jsonItems = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("items.json"))

    for (jsonBlock in jsonBlocks) {
        val id = jsonBlock["id"].asInt()

    }

}


fun formatName(name: String): String {
    return name.replace(" ", "_").uppercase()
}