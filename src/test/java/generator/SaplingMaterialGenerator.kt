package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class SaplingMaterialGenerator : MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        WoodType.values().forEach {
            results.add(MaterialGeneratorResult("${it.name}_${name}", id, it.id))
        }

        return results
    }

}

enum class WoodType(val id: Int) {
    OAK(0),
    SPRUCE(1),
    BIRCH(2),
    JUNGLE(3),
    ACACIA(4),
    DARK_OAK(5),
}