package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.wrapper.ContextualWrapper
import eu.mshade.enderframe.wrapper.MaterialKeyWrapper
import eu.mshade.enderframe.wrapper.MaterialWrapperContext
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.enderman.EndermanMaterial

class EndermanMaterialKeyWrapper : MaterialKeyWrapper() {
    /**
     * Class for wrapping a MaterialKey for protocol 1.8.9
     */
    init {
        //list minecraft materials id
        register(Material.AIR, MaterialKey.from(0))

        register(Material.STONE, MaterialKey.from(1, 0))
        register(Material.GRANITE, MaterialKey.from(1, 1))
        register(Material.POLISHED_GRANITE, MaterialKey.from(1, 2))
        register(Material.DIORITE, MaterialKey.from(1, 3))
        register(Material.POLISHED_DIORITE, MaterialKey.from(1, 4))
        register(Material.ANDESITE, MaterialKey.from(1, 5))
        register(Material.POLISHED_ANDESITE, MaterialKey.from(1, 6))

        register(Material.GRASS_BLOCK, MaterialKey.from(2))

        register(Material.DIRT, MaterialKey.from(3))
        register(Material.COARSE_DIRT, MaterialKey.from(3, 1))
        register(Material.PODZOL, MaterialKey.from(3, 2))
        register(Material.COBBLESTONE, MaterialKey.from(4))

        register(Material.OAK_PLANKS, MaterialKey.from(5))
        register(Material.SPRUCE_PLANKS, MaterialKey.from(5, 1))
        register(Material.BIRCH_PLANKS, MaterialKey.from(5, 2))
        register(Material.JUNGLE_PLANKS, MaterialKey.from(5, 3))
        register(Material.ACACIA_PLANKS, MaterialKey.from(5, 4))
        register(Material.DARK_OAK_PLANKS, MaterialKey.from(5, 5))

        register(Material.OAK_SAPLING, MaterialKey.from(6))
        register(Material.SPRUCE_SAPLING, MaterialKey.from(6, 1))
        register(Material.BIRCH_SAPLING, MaterialKey.from(6, 2))
        register(Material.JUNGLE_SAPLING, MaterialKey.from(6, 3))
        register(Material.ACACIA_SAPLING, MaterialKey.from(6, 4))
        register(Material.DARK_OAK_SAPLING, MaterialKey.from(6, 5))

        register(Material.BEDROCK, MaterialKey.from(7))



        //register slab
        register(Material.STONE_SLAB, MaterialKey.from(44, 0))
        register(Material.SANDSTONE_SLAB, MaterialKey.from(44, 1))
        registerReverseMapping(Material.OAK_SLAB, MaterialKey.from(44, 2))
        register(Material.COBBLESTONE_SLAB, MaterialKey.from(44, 3))
        register(Material.BRICK_SLAB, MaterialKey.from(44, 4))
        register(Material.STONE_BRICK_SLAB, MaterialKey.from(44, 5))
        register(Material.NETHER_BRICK_SLAB, MaterialKey.from(44, 6))
        register(Material.QUARTZ_SLAB, MaterialKey.from(44, 7))
        register(Material.OAK_SLAB, MaterialKey.from(126, 0))
        register(Material.SPRUCE_SLAB, MaterialKey.from(126, 1))
        register(Material.BIRCH_SLAB, MaterialKey.from(126, 2))
        register(Material.JUNGLE_SLAB, MaterialKey.from(126, 3))
        register(Material.ACACIA_SLAB, MaterialKey.from(126, 4))
        register(Material.DARK_OAK_SLAB, MaterialKey.from(126, 5))
        register(Material.PURPUR_SLAB, MaterialKey.from(205))

        registerReverseMapping(Material.STONE_SLAB, EndermanMaterial.DOUBLE_STONE_SLAB)
        registerReverseMapping(Material.SANDSTONE_SLAB, EndermanMaterial.DOUBLE_SANDSTONE_SLAB)
        registerReverseMapping(Material.COBBLESTONE_SLAB, EndermanMaterial.DOUBLE_COBBLESTONE_SLAB)
        registerReverseMapping(Material.BRICK_SLAB, EndermanMaterial.DOUBLE_BRICK_SLAB)
        registerReverseMapping(Material.STONE_BRICK_SLAB, EndermanMaterial.DOUBLE_STONE_BRICK_SLAB)
        registerReverseMapping(Material.NETHER_BRICK_SLAB, EndermanMaterial.DOUBLE_NETHER_BRICK_SLAB)
        registerReverseMapping(Material.QUARTZ_SLAB, EndermanMaterial.DOUBLE_QUARTZ_SLAB)
        registerReverseMapping(Material.OAK_SLAB, EndermanMaterial.DOUBLE_OAK_SLAB)
        registerReverseMapping(Material.SPRUCE_SLAB, EndermanMaterial.DOUBLE_SPRUCE_SLAB)
        registerReverseMapping(Material.BIRCH_SLAB, EndermanMaterial.DOUBLE_BIRCH_SLAB)
        registerReverseMapping(Material.JUNGLE_SLAB, EndermanMaterial.DOUBLE_JUNGLE_SLAB)
        registerReverseMapping(Material.ACACIA_SLAB, EndermanMaterial.DOUBLE_ACACIA_SLAB)
        registerReverseMapping(Material.DARK_OAK_SLAB, EndermanMaterial.DOUBLE_DARK_OAK_SLAB)
        registerReverseMapping(Material.PURPUR_SLAB, EndermanMaterial.DOUBLE_PURPUR_SLAB)


        //register wool
        register(Material.WHITE_WOOL, MaterialKey.from(35, 0))
        register(Material.ORANGE_WOOL, MaterialKey.from(35, 1))
        register(Material.MAGENTA_WOOL, MaterialKey.from(35, 2))
        register(Material.LIGHT_BLUE_WOOL, MaterialKey.from(35, 3))
        register(Material.YELLOW_WOOL, MaterialKey.from(35, 4))
        register(Material.LIME_WOOL, MaterialKey.from(35, 5))
        register(Material.PINK_WOOL, MaterialKey.from(35, 6))
        register(Material.GRAY_WOOL, MaterialKey.from(35, 7))
        register(Material.LIGHT_GRAY_WOOL, MaterialKey.from(35, 8))
        register(Material.CYAN_WOOL, MaterialKey.from(35, 9))
        register(Material.PURPLE_WOOL, MaterialKey.from(35, 10))
        register(Material.BLUE_WOOL, MaterialKey.from(35, 11))
        register(Material.BROWN_WOOL, MaterialKey.from(35, 12))
        register(Material.GREEN_WOOL, MaterialKey.from(35, 13))
        register(Material.RED_WOOL, MaterialKey.from(35, 14))
        register(Material.BLACK_WOOL, MaterialKey.from(35, 15))

        //register armor
        register(MaterialWrapperContext.ITEM, Material.LEATHER_HELMET, MaterialKey.from(298))
        register(MaterialWrapperContext.ITEM, Material.LEATHER_CHESTPLATE, MaterialKey.from(299))
        register(MaterialWrapperContext.ITEM, Material.LEATHER_LEGGINGS, MaterialKey.from(300))
        register(MaterialWrapperContext.ITEM, Material.LEATHER_BOOTS, MaterialKey.from(301))

        register(MaterialWrapperContext.ITEM, Material.CHAINMAIL_HELMET, MaterialKey.from(302))
        register(MaterialWrapperContext.ITEM, Material.CHAINMAIL_CHESTPLATE, MaterialKey.from(303))
        register(MaterialWrapperContext.ITEM, Material.CHAINMAIL_LEGGINGS, MaterialKey.from(304))
        register(MaterialWrapperContext.ITEM, Material.CHAINMAIL_BOOTS, MaterialKey.from(305))

        register(MaterialWrapperContext.ITEM, Material.IRON_HELMET, MaterialKey.from(306))
        register(MaterialWrapperContext.ITEM, Material.IRON_CHESTPLATE, MaterialKey.from(307))
        register(MaterialWrapperContext.ITEM, Material.IRON_LEGGINGS, MaterialKey.from(308))
        register(MaterialWrapperContext.ITEM, Material.IRON_BOOTS, MaterialKey.from(309))

        register(MaterialWrapperContext.ITEM, Material.GOLDEN_HELMET, MaterialKey.from(314))
        register(MaterialWrapperContext.ITEM, Material.GOLDEN_CHESTPLATE, MaterialKey.from(315))
        register(MaterialWrapperContext.ITEM, Material.GOLDEN_LEGGINGS, MaterialKey.from(316))
        register(MaterialWrapperContext.ITEM, Material.GOLDEN_BOOTS, MaterialKey.from(317))

        register(MaterialWrapperContext.ITEM, Material.DIAMOND_HELMET, MaterialKey.from(310))
        register(MaterialWrapperContext.ITEM, Material.DIAMOND_CHESTPLATE, MaterialKey.from(311))
        register(MaterialWrapperContext.ITEM, Material.DIAMOND_LEGGINGS, MaterialKey.from(312))
        register(MaterialWrapperContext.ITEM, Material.DIAMOND_BOOTS, MaterialKey.from(313))

    }

}