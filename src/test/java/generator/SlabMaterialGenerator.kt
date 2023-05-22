package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class SlabMaterialGenerator : MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        SlabType.values().forEach {
            results.add(MaterialGeneratorResult("${it.name}_SLAB", id, it.id))
        }

        return results
    }
}

enum class SlabType(val id: Int) {
    STONE(0),
    SANDSTONE(1),
    OAK(2),
    COBBLESTONE(3),
    BRICK(4),
    STONE_BRICK(5),
    NETHER_BRICK(6),
    QUARTZ(7),
}