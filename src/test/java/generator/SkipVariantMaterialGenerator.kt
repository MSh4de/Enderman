package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class SkipVariantMaterialGenerator : MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        return listOf(MaterialGeneratorResult(name, id, 0))
    }
}