package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class InfestedStoneMaterialGenerator : MaterialGenerator {
    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        InfestedStoneType.values().forEach {
            results.add(MaterialGeneratorResult("${it.name}", id, it.id))
        }

        return results
    }


}

enum class InfestedStoneType(val id: Int) {
    INFESTED_STONE(0),
    INFESTED_COBBLESTONE(1),
    INFESTED_STONE_BRICKS(2),
    INFESTED_MOSSY_STONE_BRICKS(3),
    INFESTED_CRACKED_STONE_BRICKS(4),
    INFESTED_CHISELED_STONE_BRICKS(5),
}