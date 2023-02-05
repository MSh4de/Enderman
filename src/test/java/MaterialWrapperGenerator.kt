import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

fun main() {
    val mapper = ObjectMapper()
    val json = mapper.readTree(File("./1.8.json"))
    val jsonNode = json[0]
    val blocks = jsonNode["blocks"]["block"]
    blocks.forEach{
        val name = it["text_id"].asText()
        val id = it["numeric_id"].asInt()
        println(it["text_id"].asText())
    }
}