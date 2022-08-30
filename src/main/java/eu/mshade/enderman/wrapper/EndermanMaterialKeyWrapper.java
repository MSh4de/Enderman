package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.wrapper.Wrapper;


public class EndermanMaterialWrapper extends Wrapper<MaterialKey, MaterialKey> {

    /**
     * Class for wrapping a MaterialKey for protocol 1.8.9
     */

    public EndermanMaterialWrapper() {
        //list minecraft materials id

        register(Material.AIR, MaterialKey.from(0));
        register(Material.STONE, MaterialKey.from(1, 0));
        register(Material.GRANITE, MaterialKey.from(1, 1));
        register(Material.POLISHED_GRANITE, MaterialKey.from(1, 2));
        register(Material.DIORITE, MaterialKey.from(1, 3));
        register(Material.POLISHED_DIORITE, MaterialKey.from(1, 4));
        register(Material.ANDESITE, MaterialKey.from(1, 5));
        register(Material.POLISHED_ANDESITE, MaterialKey.from(1, 6));

        register(Material.GRASS, MaterialKey.from(2));

        register(Material.DIRT, MaterialKey.from(3));
        register(Material.COARSE_DIRT, MaterialKey.from(3, 1));
        register(Material.PODZOL, MaterialKey.from(3, 2));

        register(Material.COBBLESTONE, MaterialKey.from(4));

        register(Material.OAK_WOOD_PLANKS, MaterialKey.from(5));
        register(Material.SPRUCE_WOOD_PLANKS, MaterialKey.from(5, 1));
        register(Material.BIRCH_WOOD_PLANKS, MaterialKey.from(5, 2));
        register(Material.JUNGLE_WOOD_PLANKS, MaterialKey.from(5, 3));
        register(Material.ACACIA_WOOD_PLANKS, MaterialKey.from(5, 4));
        register(Material.DARK_OAK_WOOD_PLANKS, MaterialKey.from(5, 5));

        register(Material.OAK_SAPLING, MaterialKey.from(6));
        register(Material.SPRUCE_SAPLING, MaterialKey.from(6, 1));
        register(Material.BIRCH_SAPLING, MaterialKey.from(6, 2));
        register(Material.JUNGLE_SAPLING, MaterialKey.from(6, 3));
        register(Material.ACACIA_SAPLING, MaterialKey.from(6, 4));
        register(Material.DARK_OAK_SAPLING, MaterialKey.from(6, 5));

        register(Material.BEDROCK, MaterialKey.from(7));
        register(Material.WATER, MaterialKey.from(8));
        register(Material.FLOWING_WATER, MaterialKey.from(9));
        register(Material.LAVA, MaterialKey.from(10));
        register(Material.FLOWING_LAVA, MaterialKey.from(11));

        register(Material.SAND, MaterialKey.from(12));
        register(Material.RED_SAND, MaterialKey.from(12, 1));
        register(Material.GRAVEL, MaterialKey.from(13));
        register(Material.GOLD_ORE, MaterialKey.from(14));
        register(Material.IRON_ORE, MaterialKey.from(15));
        register(Material.COAL_ORE, MaterialKey.from(16));

        register(Material.OAK_LOG, MaterialKey.from(17));
        register(Material.SPRUCE_LOG, MaterialKey.from(17, 1));
        register(Material.BIRCH_LOG, MaterialKey.from(17, 2));
        register(Material.JUNGLE_LOG, MaterialKey.from(17, 3));
        register(Material.ACACIA_LOG, MaterialKey.from(162));
        register(Material.DARK_OAK_LOG, MaterialKey.from(162, 1));

        register(Material.OAK_LEAVES, MaterialKey.from(18));
        register(Material.SPRUCE_LEAVES, MaterialKey.from(18, 1));
        register(Material.BIRCH_LEAVES, MaterialKey.from(18, 2));
        register(Material.JUNGLE_LEAVES, MaterialKey.from(18, 3));
        register(Material.ACACIA_LEAVES, MaterialKey.from(161));
        register(Material.DARK_OAK_LEAVES, MaterialKey.from(161, 1));

        register(Material.SPONGE, MaterialKey.from(19));
        register(Material.WET_SPONGE, MaterialKey.from(19, 1));

        register(Material.GLASS, MaterialKey.from(20));

        register(Material.LAPIS_ORE, MaterialKey.from(21));
        register(Material.LAPIS_BLOCK, MaterialKey.from(22));
        register(Material.DISPENSER, MaterialKey.from(23));

        register(Material.SANDSTONE, MaterialKey.from(24));
        register(Material.CHISELED_SANDSTONE, MaterialKey.from(24, 1));
        register(Material.SMOOTH_SANDSTONE, MaterialKey.from(24, 2));

        register(Material.NOTE_BLOCK, MaterialKey.from(25));

        registerInput(Material.WHITE_BED, MaterialKey.from(26));
        registerInput(Material.ORANGE_BED, MaterialKey.from(26));
        registerInput(Material.MAGENTA_BED, MaterialKey.from(26));
        registerInput(Material.LIGHT_BLUE_BED, MaterialKey.from(26));
        registerInput(Material.YELLOW_BED, MaterialKey.from(26));
        registerInput(Material.LIME_BED, MaterialKey.from(26));
        registerInput(Material.PINK_BED, MaterialKey.from(26));
        registerInput(Material.GRAY_BED, MaterialKey.from(26));
        registerInput(Material.LIGHT_GRAY_BED, MaterialKey.from(26));
        registerInput(Material.CYAN_BED, MaterialKey.from(26));
        registerInput(Material.PURPLE_BED, MaterialKey.from(26));
        registerInput(Material.BLUE_BED, MaterialKey.from(26));
        registerInput(Material.BROWN_BED, MaterialKey.from(26));
        registerInput(Material.GREEN_BED, MaterialKey.from(26));
        register(Material.RED_BED, MaterialKey.from(26));
        registerInput(Material.BLACK_BED, MaterialKey.from(26));

        register(Material.POWERED_RAIL, MaterialKey.from(27));
        register(Material.DETECTOR_RAIL, MaterialKey.from(28));
        register(Material.STICKY_PISTON, MaterialKey.from(29));
        register(Material.COBWEB, MaterialKey.from(30));

        register(Material.DEAD_SHRUB, MaterialKey.from(31));
        register(Material.TALL_GRASS, MaterialKey.from(31, 1));
        register(Material.FERN, MaterialKey.from(31, 2));
        register(Material.DEAD_BUSH, MaterialKey.from(32));
        register(Material.PISTON, MaterialKey.from(33));
        // register piston head
        register(Material.PISTON_HEAD, MaterialKey.from(34));

        register(Material.WHITE_WOOL, MaterialKey.from(35));
        register(Material.ORANGE_WOOL, MaterialKey.from(35, 1));
        register(Material.MAGENTA_WOOL, MaterialKey.from(35, 2));
        register(Material.LIGHT_BLUE_WOOL, MaterialKey.from(35, 3));
        register(Material.YELLOW_WOOL, MaterialKey.from(35, 4));
        register(Material.LIME_WOOL, MaterialKey.from(35, 5));
        register(Material.PINK_WOOL, MaterialKey.from(35, 6));
        register(Material.GRAY_WOOL, MaterialKey.from(35, 7));
        register(Material.LIGHT_GRAY_WOOL, MaterialKey.from(35, 8));
        register(Material.CYAN_WOOL, MaterialKey.from(35, 9));
        register(Material.PURPLE_WOOL, MaterialKey.from(35, 10));
        register(Material.BLUE_WOOL, MaterialKey.from(35, 11));
        register(Material.BROWN_WOOL, MaterialKey.from(35, 12));
        register(Material.GREEN_WOOL, MaterialKey.from(35, 13));
        register(Material.RED_WOOL, MaterialKey.from(35, 14));
        register(Material.BLACK_WOOL, MaterialKey.from(35, 15));

        register(Material.DANDELION, MaterialKey.from(37));
        register(Material.POPPY, MaterialKey.from(38));
        register(Material.BLUE_ORCHID, MaterialKey.from(38, 1));
        register(Material.ALLIUM, MaterialKey.from(38, 2));
        register(Material.AZURE_BLUET, MaterialKey.from(38, 3));
        register(Material.RED_TULIP, MaterialKey.from(38, 4));
        register(Material.ORANGE_TULIP, MaterialKey.from(38, 5));
        register(Material.WHITE_TULIP, MaterialKey.from(38, 6));
        register(Material.PINK_TULIP, MaterialKey.from(38, 7));
        register(Material.OXEYE_DAISY, MaterialKey.from(38, 8));

        register(Material.BROWN_MUSHROOM, MaterialKey.from(39));
        register(Material.RED_MUSHROOM, MaterialKey.from(40));
        register(Material.GOLD_BLOCK, MaterialKey.from(41));
        register(Material.IRON_BLOCK, MaterialKey.from(42));

        register(Material.DOUBLE_STONE_SLAB, MaterialKey.from(43));
        register(Material.DOUBLE_SANDSTONE_SLAB, MaterialKey.from(43, 1));
        register(Material.DOUBLE_WOODEN_SLAB, MaterialKey.from(43, 2));
        register(Material.DOUBLE_COBBLESTONE_SLAB, MaterialKey.from(43, 3));
        register(Material.DOUBLE_BRICK_SLAB, MaterialKey.from(43, 4));
        register(Material.DOUBLE_STONE_BRICK_SLAB, MaterialKey.from(43, 5));
        register(Material.DOUBLE_NETHER_BRICK_SLAB, MaterialKey.from(43, 6));
        register(Material.DOUBLE_QUARTZ_SLAB, MaterialKey.from(43, 7));

        register(Material.STONE_SLAB, MaterialKey.from(44));
        register(Material.SANDSTONE_SLAB, MaterialKey.from(44, 1));
        register(Material.WOODEN_SLAB, MaterialKey.from(44, 2));
        register(Material.COBBLESTONE_SLAB, MaterialKey.from(44, 3));
        register(Material.BRICK_SLAB, MaterialKey.from(44, 4));
        register(Material.STONE_BRICK_SLAB, MaterialKey.from(44, 5));
        register(Material.NETHER_BRICK_SLAB, MaterialKey.from(44, 6));
        register(Material.QUARTZ_SLAB, MaterialKey.from(44, 7));

        register(Material.BRICKS, MaterialKey.from(45));
        register(Material.TNT, MaterialKey.from(46));
        register(Material.BOOKSHELF, MaterialKey.from(47));
        register(Material.MOSSY_COBBLESTONE, MaterialKey.from(48));
        register(Material.OBSIDIAN, MaterialKey.from(49));
        register(Material.TORCH, MaterialKey.from(50));
        register(Material.FIRE, MaterialKey.from(51));
        register(Material.MOB_SPAWNER, MaterialKey.from(52));
        register(Material.OAK_WOOD_STAIRS, MaterialKey.from(53));
        register(Material.CHEST, MaterialKey.from(54));
        register(Material.REDSTONE_WIRE, MaterialKey.from(55));
        register(Material.DIAMOND_ORE, MaterialKey.from(56));
        register(Material.DIAMOND_BLOCK, MaterialKey.from(57));
        register(Material.CRAFTING_TABLE, MaterialKey.from(58));
        register(Material.WHEAT, MaterialKey.from(59));
        register(Material.FARMLAND, MaterialKey.from(60));
        register(Material.FURNACE, MaterialKey.from(61));
        register(Material.LIT_FURNACE, MaterialKey.from(62));
        register(Material.STANDING_SIGN, MaterialKey.from(63));
        register(Material.OAK_DOOR, MaterialKey.from(64));
        register(Material.LADDER, MaterialKey.from(65));
        register(Material.RAIL, MaterialKey.from(66));
        register(Material.COBBLESTONE_STAIRS, MaterialKey.from(67));
        register(Material.WALL_SIGN, MaterialKey.from(68));
        register(Material.LEVER, MaterialKey.from(69));
        register(Material.STONE_PRESSURE_PLATE, MaterialKey.from(70));
        register(Material.IRON_DOOR, MaterialKey.from(71));
        register(Material.OAK_PRESSURE_PLATE, MaterialKey.from(72));
        register(Material.REDSTONE_ORE, MaterialKey.from(73));
        register(Material.LIT_REDSTONE_ORE, MaterialKey.from(74));
        register(Material.UNLIT_REDSTONE_TORCH, MaterialKey.from(75));
        register(Material.REDSTONE_TORCH, MaterialKey.from(76));
        register(Material.STONE_BUTTON, MaterialKey.from(77));
        register(Material.SNOW, MaterialKey.from(78));
        register(Material.ICE, MaterialKey.from(79));
        register(Material.SNOW_BLOCK, MaterialKey.from(80));
        register(Material.CACTUS, MaterialKey.from(81));
        register(Material.CLAY, MaterialKey.from(82));
        register(Material.SUGAR_CANE, MaterialKey.from(83));
        register(Material.JUKEBOX, MaterialKey.from(84));
        register(Material.OAK_FENCE, MaterialKey.from(85));
        register(Material.PUMPKIN, MaterialKey.from(86));
        register(Material.NETHERRACK, MaterialKey.from(87));
        register(Material.SOUL_SAND, MaterialKey.from(88));
        register(Material.GLOWSTONE, MaterialKey.from(89));
        register(Material.NETHER_PORTAL, MaterialKey.from(90));
        register(Material.JACK_O_LANTERN, MaterialKey.from(91));
        register(Material.CAKE, MaterialKey.from(92));
        register(Material.UNPOWERED_REPEATER, MaterialKey.from(93));
        register(Material.POWERED_REPEATER, MaterialKey.from(94));



        // register stained glass with colors starting id 95
        register(Material.WHITE_STAINED_GLASS, MaterialKey.from(95));
        register(Material.ORANGE_STAINED_GLASS, MaterialKey.from(95, 1));
        register(Material.MAGENTA_STAINED_GLASS, MaterialKey.from(95, 2));
        register(Material.LIGHT_BLUE_STAINED_GLASS, MaterialKey.from(95, 3));
        register(Material.YELLOW_STAINED_GLASS, MaterialKey.from(95, 4));
        register(Material.LIME_STAINED_GLASS, MaterialKey.from(95, 5));
        register(Material.PINK_STAINED_GLASS, MaterialKey.from(95, 6));
        register(Material.GRAY_STAINED_GLASS, MaterialKey.from(95, 7));
        register(Material.LIGHT_GRAY_STAINED_GLASS, MaterialKey.from(95, 8));
        register(Material.CYAN_STAINED_GLASS, MaterialKey.from(95, 9));
        register(Material.PURPLE_STAINED_GLASS, MaterialKey.from(95, 10));
        register(Material.BLUE_STAINED_GLASS, MaterialKey.from(95, 11));
        register(Material.BROWN_STAINED_GLASS, MaterialKey.from(95, 12));
        register(Material.GREEN_STAINED_GLASS, MaterialKey.from(95, 13));
        register(Material.RED_STAINED_GLASS, MaterialKey.from(95, 14));
        register(Material.BLACK_STAINED_GLASS, MaterialKey.from(95, 15));

        register(Material.OAK_TRAPDOOR, MaterialKey.from(96));
        register(Material.STONE_MONSTER_EGG, MaterialKey.from(97));
        register(Material.COBBLESTONE_MONSTER_EGG, MaterialKey.from(97, 1));
        register(Material.STONE_BRICK_MONSTER_EGG, MaterialKey.from(97, 2));
        register(Material.MOSSY_STONE_BRICK_MONSTER_EGG, MaterialKey.from(97, 3));
        register(Material.CRACKED_STONE_BRICK_MONSTER_EGG, MaterialKey.from(97, 4));
        register(Material.CHISELED_STONE_BRICK_MONSTER_EGG, MaterialKey.from(97, 5));

        register(Material.STONE_BRICK, MaterialKey.from(98));
        register(Material.MOSSY_STONE_BRICK, MaterialKey.from(98, 1));
        register(Material.CRACKED_STONE_BRICK, MaterialKey.from(98, 2));
        register(Material.CHISELED_STONE_BRICK, MaterialKey.from(98, 3));

        register(Material.BROWN_MUSHROOM_BLOCK, MaterialKey.from(99));
        register(Material.RED_MUSHROOM_BLOCK, MaterialKey.from(100));

        register(Material.IRON_BARS, MaterialKey.from(101));
        register(Material.GLASS_PANE, MaterialKey.from(102));
        register(Material.MELON_BLOCK, MaterialKey.from(103));
        register(Material.PUMPKIN_STEM, MaterialKey.from(104));
        register(Material.MELON_STEM, MaterialKey.from(105));
        register(Material.VINE, MaterialKey.from(106));
        register(Material.OAK_FENCE_GATE, MaterialKey.from(107));
        register(Material.BRICK_STAIRS, MaterialKey.from(108));
        register(Material.STONE_BRICK_STAIRS, MaterialKey.from(109));
        register(Material.MYCELIUM, MaterialKey.from(110));
        register(Material.LILY_PAD, MaterialKey.from(111));
        register(Material.NETHER_BRICK, MaterialKey.from(112));
        register(Material.NETHER_BRICK_FENCE, MaterialKey.from(113));
        register(Material.NETHER_BRICK_STAIRS, MaterialKey.from(114));
        register(Material.NETHER_WART, MaterialKey.from(115));
        register(Material.ENCHANTING_TABLE, MaterialKey.from(116));
        register(Material.BREWING_STAND, MaterialKey.from(117));
        register(Material.CAULDRON, MaterialKey.from(118));
        register(Material.END_PORTAL, MaterialKey.from(119));
        register(Material.END_PORTAL_FRAME, MaterialKey.from(120));
        register(Material.END_STONE, MaterialKey.from(121));
        register(Material.DRAGON_EGG, MaterialKey.from(122));
        register(Material.REDSTONE_LAMP, MaterialKey.from(123));
        register(Material.LIT_REDSTONE_LAMP, MaterialKey.from(124));
        register(Material.DOUBLE_OAK_SLAB, MaterialKey.from(125));
        register(Material.DOUBLE_SPRUCE_SLAB, MaterialKey.from(125, 1));
        register(Material.DOUBLE_BIRCH_SLAB, MaterialKey.from(125, 2));
        register(Material.DOUBLE_JUNGLE_SLAB, MaterialKey.from(125, 3));
        register(Material.DOUBLE_ACACIA_SLAB, MaterialKey.from(125, 4));
        register(Material.DOUBLE_DARK_OAK_SLAB, MaterialKey.from(125, 5));

        register(Material.OAK_SLAB, MaterialKey.from(126));
        register(Material.SPRUCE_SLAB, MaterialKey.from(126, 1));
        register(Material.BIRCH_SLAB, MaterialKey.from(126, 2));
        register(Material.JUNGLE_SLAB, MaterialKey.from(126, 3));
        register(Material.ACACIA_SLAB, MaterialKey.from(126, 4));
        register(Material.DARK_OAK_SLAB, MaterialKey.from(126, 5));

        register(Material.COCOA, MaterialKey.from(127));
        register(Material.SANDSTONE_STAIRS, MaterialKey.from(128));
        register(Material.EMERALD_ORE, MaterialKey.from(129));
        register(Material.ENDER_CHEST, MaterialKey.from(130));
        register(Material.TRIPWIRE_HOOK, MaterialKey.from(131));
        register(Material.TRIPWIRE, MaterialKey.from(132));
        register(Material.EMERALD_BLOCK, MaterialKey.from(133));

        register(Material.SPRUCE_STAIRS, MaterialKey.from(134));
        register(Material.BIRCH_STAIRS, MaterialKey.from(135));
        register(Material.JUNGLE_STAIRS, MaterialKey.from(136));


        register(Material.HAY_BLOCK, MaterialKey.from(170));


        /*
        register(Material.WOODEN_PICKAXE, MaterialKey.from(270));
        register(Material.LEATHER_HELMET, MaterialKey.from(298));
        register(Material.PLAYER_HEAD, MaterialKey.from(397, 3));

         */


    }


}
