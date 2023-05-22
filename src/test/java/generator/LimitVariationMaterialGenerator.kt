package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode
import minecraftFormat

class LimitVariationMaterialGenerator(private val limit: Int): MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        variations?.forEachIndexed{ index, variation ->
            if (index < limit) {
                val meta = variation["metadata"].asInt()
                val variationName = variation["displayName"].asText()
                results.add(
                    MaterialGeneratorResult(
                        minecraftFormat(variationName.uppercase()),
                        id,
                        meta
                    )
                )
            }
        }
        return results
    }
}