import com.fasterxml.jackson.databind.ObjectMapper
import generator.ColorMaterialGenerator

fun main() {
    val mapper = ObjectMapper()
    val jsonBlocks = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("blocks.json"))
    val jsonItems = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("items.json"))

    val materialGeneratorRegistry = MaterialGeneratorRegistry()
    materialGeneratorRegistry.registerMaterialGenerator(listOf("wool", "carpet", "stained_glass", "stained_glass_pane", "concrete", "concrete_powder", "stained_hardened_clay"), ColorMaterialGenerator())

    materialGeneratorRegistry.registerMaterialReplacer("hardened_clay", "terracotta")
    materialGeneratorRegistry.registerMaterialReplacer("stained_hardened_clay", "terracotta")

    val materialRegistered = mutableListOf<MaterialGeneratorResult>()


    for (jsonBlock in jsonBlocks) {
        val id = jsonBlock["id"].asInt()
        val name = jsonBlock["name"].asText()

        val variations = jsonBlock["variations"]
        val materialGenerator = materialGeneratorRegistry.getMaterialGenerator(name)

        var displayName = name
        if (materialGeneratorRegistry.getMaterialReplacer(name) != null) {
            displayName = materialGeneratorRegistry.getMaterialReplacer(name)
        }

        if (variations != null) {
            if (materialGenerator != null) {
                val results = materialGenerator.generateVariations(id, displayName.uppercase(), variations)
                for (result in results) {
                    materialRegistered.add(result)
                }
            }else{

                for (variation in variations) {
                    val meta = variation["metadata"].asInt()
                    val variationName = variation["displayName"].asText()
                    materialRegistered.add(MaterialGeneratorResult(minecraftFormat(variationName.uppercase()), id, meta))
                }

            }
        } else {
            if (materialGenerator != null) {
                val result = materialGenerator.generateVariations(id, displayName.uppercase(), null)
                result.forEach {
                    materialRegistered.add(it)
                }
            }else{
                materialRegistered.add(MaterialGeneratorResult(displayName.uppercase(), id, 0))
            }
        }
    }

    materialRegistered.forEach {
        println("material registered: $it")
    }

}

fun minecraftFormat(name: String): String {
    return name.replace(" ", "_").uppercase()
}

