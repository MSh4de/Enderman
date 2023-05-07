import com.fasterxml.jackson.databind.JsonNode

class MaterialGeneratorRegistry {

    private val materialGenerators = HashMap<String, MaterialGenerator>()
    private val materialReplacer = HashMap<String, String>()

    fun registerMaterialGenerator(name: String, materialGenerator: MaterialGenerator) {
        materialGenerators[name] = materialGenerator
    }

    fun registerMaterialGenerator(names: List<String>, materialGenerator: MaterialGenerator) {
        for (name in names) {
            registerMaterialGenerator(name, materialGenerator)
        }
    }

    fun getMaterialGenerator(name: String): MaterialGenerator? {
        return materialGenerators[name]
    }

    fun registerMaterialReplacer(name: String, replacer: String) {
        materialReplacer[name] = replacer
    }

    fun getMaterialReplacer(name: String): String? {
        return materialReplacer[name]
    }

}


interface MaterialGenerator {

    fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult>

}

data class MaterialGeneratorResult(val name: String, val id: Int, val meta: Int)