package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class SplitNameMaterialGenerator(val splitter: (String, String) -> String): MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val generatorResults = mutableListOf<MaterialGeneratorResult>()
        val nameUsed = mutableSetOf<String>()

        if (variations != null) {
            for (variation in variations) {
                val meta = variation["metadata"].asInt()
                val variationName = variation["displayName"].asText()
                val materialGeneratorResult = MaterialGeneratorResult(splitter(name, variationName.uppercase()), id, meta)
                if (!nameUsed.contains(materialGeneratorResult.name)) {
                    generatorResults.add(materialGeneratorResult)
                    nameUsed.add(materialGeneratorResult.name)
                }
            }
        }else {
            generatorResults.add(MaterialGeneratorResult(splitter(name, name.uppercase()), id, 0))
        }

        return generatorResults
    }



}