import com.fasterxml.jackson.databind.ObjectMapper
import generator.ColorMaterialGenerator

fun main() {
    val mapper = ObjectMapper()
    val jsonBlocks = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("blocks.json"))
    val jsonItems = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("items.json"))

    val materialGeneratorRegistry = MaterialGeneratorRegistry()
    materialGeneratorRegistry.registerMaterialGenerator(listOf("wool", "carpet", "stained_glass", "stained_glass_pane", "concrete", "concrete_powder", "stained_hardened_clay"), ColorMaterialGenerator())

    materialGeneratorRegistry.registerMaterialReplacer("hardened_clay", "TERRACOTTA")
    materialGeneratorRegistry.registerMaterialReplacer("stained_hardened_clay", "TERRACOTTA")

    val materialRegistered = mutableListOf<MaterialGeneratorResult>()


    for (jsonBlock in jsonBlocks) {
        val id = jsonBlock["id"].asInt()
        val name = jsonBlock["name"].asText()

        val variations = jsonBlock["variations"]
        val materialGenerator = materialGeneratorRegistry.getMaterialGenerator(name)

        if (variations != null) {
            if (materialGenerator != null) {
                val results = materialGenerator.generateVariations(id, name.uppercase(), variations)
                for (result in results) {
                    materialRegistered.add(result)
                }
            }
        } else {



            materialGenerator?.generateVariations(id, name.uppercase(), null)?.forEach(materialRegistered::add)
        }
    }

    materialRegistered.forEach {
        println("material registered: $it")
    }

}

