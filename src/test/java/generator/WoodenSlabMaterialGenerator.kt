package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class WoodenSlabMaterialGenerator: MaterialGenerator {
    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        WoodenSlabType.values().forEach {
            results.add(MaterialGeneratorResult("${it.name}_SLAB", id, it.id))
        }

        return results
    }
}

enum class WoodenSlabType(val id: Int) {
    OAK(0),
    SPRUCE(1),
    BIRCH(2),
    JUNGLE(3),
    ACACIA(4),
    DARK_OAK(5),
}