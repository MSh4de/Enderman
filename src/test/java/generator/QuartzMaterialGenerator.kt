package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class QuartzMaterialGenerator: MaterialGenerator {
    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        QuartzType.values().forEach {
            results.add(MaterialGeneratorResult(it.name, id, it.id))
        }

        return results
    }
}

enum class QuartzType(val id: Int) {
    QUARTZ_BLOCK(0),
    CHISELED_QUARTZ_BLOCK(1),
    QUARTZ_PILLAR(2),
}