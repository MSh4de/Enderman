package generator

import MaterialGenerator
import MaterialGeneratorResult
import com.fasterxml.jackson.databind.JsonNode

class SpawnEggMaterialGenerator : MaterialGenerator {
    override fun generateVariations(id: Int, name: String, variations: JsonNode?): List<MaterialGeneratorResult> {
        val results = mutableListOf<MaterialGeneratorResult>()

        EggType.values().forEach { eggType ->
            results.add(
                MaterialGeneratorResult(
                    "${eggType.name}_SPAWN_EGG",
                    id,
                    eggType.data
                )
            )
        }

        return results
    }

}

enum class EggType(val data: Int) {
    CREEPER(50),
    SKELETON(51),
    SPIDER(52),
    ZOMBIE(54),
    SLIME(55),
    GHAST(56),
    ZOMBIFIED_PIGLIN(57),
    ENDERMAN(58),
    CAVE_SPIDER(59),
    SILVERFISH(60),
    BLAZE(61),
    MAGMA_CUBE(62),
    BAT(65),
    WITCH(66),
    ENDERMITE(67),
    GUARDIAN(68),
    PIG(90),
    SHEEP(91),
    COW(92),
    CHICKEN(93),
    SQUID(94),
    WOLF(95),
    MOOSHROOM(96),
    OCELOT(98),
    HORSE(100),
    RABBIT(101),
    VILLAGER(120),

}