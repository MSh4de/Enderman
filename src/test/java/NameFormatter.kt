import com.fasterxml.jackson.databind.JsonNode

class MaterialGeneratorRegistry {

    private val materialGenerators = HashMap<String, MaterialGenerator>()
    private val materialReplacer = HashMap<String, String>()
    private val variationReplacer = HashMap<String, String>()
    private val materialBlackListByName = mutableSetOf<String>()
    private val materialBlackListById = mutableSetOf<Int>()


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

    fun registerVariationReplacer(name: String, replacer: String) {
        variationReplacer[name] = replacer
    }

    fun getVariationReplacer(name: String): String? {
        return variationReplacer[name]
    }

    fun registerMaterialBlackList(vararg names: String) {
        materialBlackListByName.addAll(names)
    }

    fun isMaterialBlackListed(name: String): Boolean {
        return materialBlackListByName.contains(name)
    }

    fun registerMaterialBlackList(vararg ids: Int) {
        ids.forEach {
            materialBlackListById.add(it)
        }
    }

    fun isMaterialBlackListed(id: Int): Boolean {
        return materialBlackListById.contains(id)
    }

    fun getMaterialVariations(): Collection<String> {
        return variationReplacer.keys
    }


}


interface MaterialGenerator {

    fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult>

}

data class MaterialGeneratorResult(var name: String, val id: Int, val meta: Int)