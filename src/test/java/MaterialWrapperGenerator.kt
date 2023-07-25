import com.fasterxml.jackson.databind.ObjectMapper
import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.wrapper.MaterialWrapperContext
import generator.*

fun main() {
    val mapper = ObjectMapper()
    val jsonBlocks = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("blocks.json"))
    val jsonItems = mapper.readTree(Thread.currentThread().contextClassLoader.getResourceAsStream("items.json"))


    Material.AIR

    val materialGeneratorRegistry = MaterialGeneratorRegistry()
    materialGeneratorRegistry.registerMaterialGenerator(
        listOf(
            "wool",
            "carpet",
            "stained_glass",
            "stained_glass_pane",
            "concrete",
            "concrete_powder",
            "stained_hardened_clay"
        ), ColorMaterialGenerator()
    )
    materialGeneratorRegistry.registerMaterialGenerator("planks", SplitNameMaterialGenerator {_, name ->
        val strings = minecraftFormat(name)
        return@SplitNameMaterialGenerator strings.replace("_WOOD", "")
    })
    materialGeneratorRegistry.registerMaterialGenerator(listOf("log", "log2"), SplitNameMaterialGenerator {mat, name ->
        if (mat == "LOG2"){
            //Acacia wood facing up/down to ACACIA_WOOD and Dark Oak wood facing up/down to DARK_OAK_WOOD
            val strings = minecraftFormat(name)
            val substringBefore = strings.substringBefore("_WOOD")

            return@SplitNameMaterialGenerator "${substringBefore}_WOOD"

        }

        val strings = minecraftFormat(name).split("_")
        if (strings.size == 1) return@SplitNameMaterialGenerator name
        return@SplitNameMaterialGenerator "${strings[0]}_${strings[1]}"
    })

    materialGeneratorRegistry.registerMaterialGenerator("brown_mushroom_block", SkipVariantMaterialGenerator())

    materialGeneratorRegistry.registerMaterialGenerator("wooden_slab", WoodenSlabMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("stone_slab", SlabMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("leaves2", LimitVariationMaterialGenerator(2))


    materialGeneratorRegistry.registerMaterialGenerator(
        listOf(
            "record_13",
            "record_car",
            "record_blocks",
            "record_chirp",
            "record_far",
            " record_mall",
            "record_mellohi",
            "record_stal",
            "record_strad",
            "record_ward",
            "record_11",
            "record_wait",
            "record_cat",
            "record_mall"
        ), SplitNameMaterialGenerator {_, name ->
            val strings = minecraftFormat(name).split("_")
            return@SplitNameMaterialGenerator "MUSIC_DISC_${strings[1]}"
        })

    materialGeneratorRegistry.registerMaterialGenerator("quartz_block", SplitNameMaterialGenerator {_,  name ->
        val strings = minecraftFormat(name).split("_")
        return@SplitNameMaterialGenerator "${strings[0]}_${strings[1]}"
    })

    materialGeneratorRegistry.registerMaterialGenerator("stonebrick", SplitNameMaterialGenerator {_,  name ->
        return@SplitNameMaterialGenerator minecraftFormat(name)+"S"
    })

    materialGeneratorRegistry.registerMaterialGenerator("monster_egg", InfestedStoneMaterialGenerator())

    materialGeneratorRegistry.registerMaterialGenerator("unlit_redstone_torch", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("quartz_block", QuartzMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("red_mushroom_block", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("tnt", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("torch", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("redstone_torch", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("snow_layer", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("jukebox", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("anvil", SkipVariantMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("stone_slab2", LimitVariationMaterialGenerator(1))
    materialGeneratorRegistry.registerMaterialGenerator("double_wooden_slab", SplitNameMaterialGenerator {_, name ->
       return@SplitNameMaterialGenerator minecraftFormat(name).substringBefore("_WOOD")+ "_SLAB"
    })
    materialGeneratorRegistry.registerMaterialGenerator("double_stone_slab", LimitVariationMaterialGenerator(9))

    materialGeneratorRegistry.registerMaterialGenerator("sapling", SaplingMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("spawn_egg", SpawnEggMaterialGenerator())
    materialGeneratorRegistry.registerMaterialGenerator("leaves", LimitVariationMaterialGenerator(4))
    materialGeneratorRegistry.registerMaterialGenerator("double_plant", LimitVariationMaterialGenerator(5))
    materialGeneratorRegistry.registerMaterialGenerator("flower_pot", SplitNameMaterialGenerator {_, name ->
        val minecraftFormat = minecraftFormat(name)
        if (minecraftFormat == "EMPTY_FLOWER_POT"){
            return@SplitNameMaterialGenerator "FLOWER_POT"
        }

        return@SplitNameMaterialGenerator "POTTED_${minecraftFormat.substringBefore("_FLOWER_POT")}"
    })

    materialGeneratorRegistry.registerMaterialGenerator("red_sandstone", SplitNameMaterialGenerator {_, name ->
        val minecraftFormat = minecraftFormat(name)
        return@SplitNameMaterialGenerator "${minecraftFormat.substringBefore("_SANDSTONE")}_RED_SANDSTONE"
    })

    materialGeneratorRegistry.registerMaterialReplacer("hardened_clay", "terracotta")
    materialGeneratorRegistry.registerMaterialReplacer("stained_hardened_clay", "terracotta")
    materialGeneratorRegistry.registerMaterialReplacer("lapis_lazuli_block", "lapis_block")
    materialGeneratorRegistry.registerMaterialReplacer("lapis_lazuli_ore", "lapis_ore")
    materialGeneratorRegistry.registerMaterialReplacer("bed", "red_bed")
    materialGeneratorRegistry.registerMaterialReplacer("golden_rail", "powered_rail")
    materialGeneratorRegistry.registerMaterialReplacer("noteblock", "note_block")
    materialGeneratorRegistry.registerMaterialReplacer("web", "cobweb")
    materialGeneratorRegistry.registerMaterialReplacer("grass", "grass_block")
    materialGeneratorRegistry.registerMaterialReplacer("tallgrass", "grass")
    materialGeneratorRegistry.registerMaterialReplacer("yellow_flower", "dandelion")
    materialGeneratorRegistry.registerMaterialReplacer("unlit_redstone_torch", "redstone_torch")
    materialGeneratorRegistry.registerMaterialReplacer("standing_sign", "oak_sign")
    materialGeneratorRegistry.registerMaterialReplacer("wall_sign", "oak_wall_sign")
    materialGeneratorRegistry.registerMaterialReplacer("brick_block", "bricks")
    materialGeneratorRegistry.registerMaterialReplacer("mob_spawner", "spawner")
    materialGeneratorRegistry.registerMaterialReplacer("quartz_ore", "nether_quartz_ore")
    materialGeneratorRegistry.registerMaterialReplacer("wooden_pressure_plate", "oak_pressure_plate")
    materialGeneratorRegistry.registerMaterialReplacer("snow_layer", "snow")
    materialGeneratorRegistry.registerMaterialReplacer("snow", "snow_block")
    materialGeneratorRegistry.registerMaterialReplacer("fence", "oak_fence")
    materialGeneratorRegistry.registerMaterialReplacer("wooden_button", "oak_button")
    materialGeneratorRegistry.registerMaterialReplacer("wooden_door", "oak_door")
    materialGeneratorRegistry.registerMaterialReplacer("sign", "oak_sign")
    materialGeneratorRegistry.registerMaterialReplacer("lit_pumpkin", "jack_o_lantern")
    materialGeneratorRegistry.registerMaterialReplacer("trapdoor", "oak_trapdoor")
    materialGeneratorRegistry.registerMaterialReplacer("boat", "oak_boat")
    materialGeneratorRegistry.registerMaterialReplacer("fence_gate", "oak_fence_gate")
    materialGeneratorRegistry.registerMaterialReplacer("speckled_melon", "glistering_melon_slice")
    materialGeneratorRegistry.registerMaterialReplacer("melon_block", "melon")
    materialGeneratorRegistry.registerMaterialReplacer("melon", "melon_slice")
    materialGeneratorRegistry.registerMaterialReplacer("waterlily", "lily_pad")
    materialGeneratorRegistry.registerMaterialReplacer("slime", "slime_block")
    materialGeneratorRegistry.registerMaterialReplacer("reeds", "sugar_cane")
    materialGeneratorRegistry.registerMaterialReplacer("unpowered_repeater", "repeater")
    materialGeneratorRegistry.registerMaterialReplacer("redstone", "redstone_wire")


    materialGeneratorRegistry.registerVariationReplacer("head", "player_head")
    materialGeneratorRegistry.registerVariationReplacer("shrub", "dead_bush")
    materialGeneratorRegistry.registerVariationReplacer("double_tallgrass", "tall_grass")
    materialGeneratorRegistry.registerVariationReplacer("cactus_green", "green_dye")
    materialGeneratorRegistry.registerVariationReplacer("rose_red", "red_dye")
    materialGeneratorRegistry.registerVariationReplacer("dandelion_yellow", "yellow_dye")
    materialGeneratorRegistry.registerVariationReplacer("zombie_pigman_spawn_egg", "zombified_piglin_spawn_egg")




    materialGeneratorRegistry.registerMaterialBlackList("dead_bush")
    materialGeneratorRegistry.registerMaterialBlackList(32, 36, 75, 94)


    val blockRegistered = mutableSetOf<MaterialGeneratorResult>()
    val itemRegistered = mutableSetOf<MaterialGeneratorResult>()

    val filteredMaterial = linkedMapOf<MaterialGeneratorResult, MutableSet<MaterialWrapperContext>>()



    for (jsonBlock in jsonBlocks) {
        val id = jsonBlock["id"].asInt()
        val name = minecraftFormat(jsonBlock["name"].asText(), false)

        val variations = jsonBlock["variations"]
        val materialGenerator = materialGeneratorRegistry.getMaterialGenerator(name)

        if (materialGeneratorRegistry.isMaterialBlackListed(name) || materialGeneratorRegistry.isMaterialBlackListed(id)) {
            continue
        }

        var displayName = name
        if (materialGeneratorRegistry.getMaterialReplacer(name) != null) {
            displayName = materialGeneratorRegistry.getMaterialReplacer(name)!!
        }


        if (variations != null) {
            if (materialGenerator != null) {
                val results = materialGenerator.generateVariations(id, displayName.uppercase(), variations)
                for (result in results) {
                    if (materialGeneratorRegistry.getVariationReplacer(result.name.lowercase()) != null) {
                        result.name = materialGeneratorRegistry.getVariationReplacer(result.name.lowercase())!!.uppercase()
                    }
                    blockRegistered.add(result)
                }
            } else {
                for (variation in variations) {
                    val meta = variation["metadata"].asInt()
                    var variationName = minecraftFormat(variation["displayName"].asText(), false)

                    if (materialGeneratorRegistry.getVariationReplacer(variationName) != null) {
                        variationName = materialGeneratorRegistry.getVariationReplacer(variationName)!!
                    }
                    blockRegistered.add(
                        MaterialGeneratorResult(
                            variationName.uppercase(),
                            id,
                            meta
                        )
                    )
                }
            }
        } else {
            if (materialGenerator != null) {
                val result = materialGenerator.generateVariations(id, displayName.uppercase(), null)
                result.forEach {
                    blockRegistered.add(it)
                }
            } else {
                blockRegistered.add(MaterialGeneratorResult(displayName.uppercase(), id, 0))
            }
        }
    }


    for (jsonItem in jsonItems) {
        val id = jsonItem["id"].asInt()
        val name = minecraftFormat(jsonItem["name"].asText(), false)

        val variations = jsonItem["variations"]
        val materialGenerator = materialGeneratorRegistry.getMaterialGenerator(name)

        if (materialGeneratorRegistry.isMaterialBlackListed(name) || materialGeneratorRegistry.isMaterialBlackListed(id)) {
            continue
        }

        var displayName = name
        if (materialGeneratorRegistry.getMaterialReplacer(name) != null) {
            displayName = materialGeneratorRegistry.getMaterialReplacer(name)!!
        }

        if (variations != null) {
            if (materialGenerator != null) {
                val results = materialGenerator.generateVariations(id, displayName.uppercase(), variations)
                for (result in results) {
                    itemRegistered.add(result)
                }
            } else {
                for (variation in variations) {
                    val meta = variation["metadata"].asInt()
                    var variationName = minecraftFormat(variation["displayName"].asText(), false)

                    if (materialGeneratorRegistry.getVariationReplacer(variationName) != null) {
                        variationName = materialGeneratorRegistry.getVariationReplacer(variationName)!!
                    }
                    itemRegistered.add(
                        MaterialGeneratorResult(
                            variationName.uppercase(),
                            id,
                            meta
                        )
                    )
                }
            }
        } else {

            val matchingMaterial = getMatchingMaterial(id, blockRegistered)
            if (!matchingMaterial.isEmpty()) {

                matchingMaterial.forEach {
                    itemRegistered.add(it)
                }
                continue
            }

            if (materialGenerator != null) {
                val result = materialGenerator.generateVariations(id, displayName.uppercase(), null)
                result.forEach {
                    itemRegistered.add(it)
                }
            } else {
                itemRegistered.add(MaterialGeneratorResult(displayName.uppercase(), id, 0))
            }
        }
    }

    itemRegistered.forEach {
        val category = getCategory(it, itemRegistered, blockRegistered)
        var materialWrapperContexts = filteredMaterial[it]

        if (materialWrapperContexts == null) {
            materialWrapperContexts = mutableSetOf()
            filteredMaterial[it] = materialWrapperContexts
        }

        for (materialWrapperContext in category) {
            materialWrapperContexts.add(materialWrapperContext)
        }
    }

    blockRegistered.forEach {
        val category = getCategory(it, itemRegistered, blockRegistered)
        var materialWrapperContexts = filteredMaterial[it]

        if (materialWrapperContexts == null) {
            materialWrapperContexts = mutableSetOf()
            filteredMaterial[it] = materialWrapperContexts!!
        }

        for (materialWrapperContext in category) {
            materialWrapperContexts!!.add(materialWrapperContext)
        }
    }

    var count = 0
    filteredMaterial.forEach {
        val (materialGeneratorResult, materialWrapperContexts) = it

/*                val fromName = Material.fromName(materialGeneratorResult.name)
                if (fromName == null){
                    println("Not find ${materialGeneratorResult.name} with id=${materialGeneratorResult.id}, meta=${materialGeneratorResult.meta}")
                    count++
                }*/

        Material.fromName(materialGeneratorResult.name) ?: return@forEach
        if (materialWrapperContexts.size == 2) {
            println("register(Material.${materialGeneratorResult.name}, MaterialKey.from(${materialGeneratorResult.id}, ${materialGeneratorResult.meta}))")
        } else {
            println("register(MaterialWrapperContext.${materialWrapperContexts.first().name}, Material.${materialGeneratorResult.name}, MaterialKey.from(${materialGeneratorResult.id}, ${materialGeneratorResult.meta}))")
        }

        count++

    }

    println("generated $count materials of ${filteredMaterial.size} materials in total")
//    println("found $count anomaly of ${filteredMaterial.size} materials in total")

}


fun minecraftFormat(name: String, upperCase: Boolean = true): String {
    val replace = name.replace(" ", "_")
    return if (upperCase) {
        replace.uppercase()
    } else {
        replace.lowercase()
    }
}


fun getCategory(
    material: MaterialGeneratorResult,
    items: Collection<MaterialGeneratorResult>,
    blocks: Collection<MaterialGeneratorResult>
): Collection<MaterialWrapperContext> {
    val item = items.find { it.id == material.id && it.name == material.name }
    val block = blocks.find { it.id == material.id && it.name == material.name }

    val contexts = mutableListOf<MaterialWrapperContext>()
    if (item != null) {
        contexts.add(MaterialWrapperContext.ITEM)
    }
    if (block != null) {
        contexts.add(MaterialWrapperContext.BLOCK)
    }
    return contexts
}


fun getMatchingMaterial(id: Int, blocks: Collection<MaterialGeneratorResult>): Collection<MaterialGeneratorResult> {
    val matching = mutableListOf<MaterialGeneratorResult>()
    for (block in blocks) {
        if (block.id == id) {
            matching.add(block)
        }
    }

    return matching
}



