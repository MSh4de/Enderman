package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

open class ColorMaterialGenerator: MaterialGenerator {

    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = ArrayList<MaterialGeneratorResult>()
        for (color in ColorMaterial.values()) {
            results.add(MaterialGeneratorResult("${color.name}_$name", id, color.ordinal))
        }
        return results
    }
}

enum class ColorMaterial {
    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK
}