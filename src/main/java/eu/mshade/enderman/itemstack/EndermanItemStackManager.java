package eu.mshade.enderman.itemstack;

import eu.mshade.enderframe.item.*;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderman.itemstack.rewriters.CommonItemStackRewriter;
import eu.mshade.enderman.itemstack.rewriters.LeatherClothItemStackRewriter;

public class EndermanItemStackManager extends ItemStackManager<MaterialData, Integer> {

    public EndermanItemStackManager() {
        super(new CommonItemStackRewriter());

        registerNamespacedKey(NamespacedKey.minecraft("wool"), Material.WHITE_WOOL, Material.BLACK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL,
                Material.BROWN_WOOL, Material.ORANGE_WOOL, Material.YELLOW_WOOL, Material.RED_WOOL, Material.LIME_WOOL, Material.GREEN_WOOL, Material.CYAN_WOOL,
                Material.BLUE_WOOL, Material.LIGHT_BLUE_WOOL, Material.PINK_WOOL, Material.PURPLE_WOOL, Material.MAGENTA_WOOL);

        registerEnchantmentWithWrap(Enchantment.PROTECTION, 0);
        registerEnchantmentWithWrap(Enchantment.FIRE_PROTECTION, 1);
        registerEnchantmentWithWrap(Enchantment.FEATHER_FALLING, 2);
        registerEnchantmentWithWrap(Enchantment.BLAST_PROTECTION, 3);
        registerEnchantmentWithWrap(Enchantment.PROJECTILE_PROTECTION, 4);
        registerEnchantmentWithWrap(Enchantment.RESPIRATION, 5);
        registerEnchantmentWithWrap(Enchantment.AQUA_AFFINITY, 6);
        registerEnchantmentWithWrap(Enchantment.THORNS, 7);
        registerEnchantmentWithWrap(Enchantment.DEPTH_STRIDER, 8);

        registerEnchantmentWithWrap(Enchantment.SHARPNESS, 16);
        registerEnchantmentWithWrap(Enchantment.SMITE, 17);
        registerEnchantmentWithWrap(Enchantment.BANE_OF_ARTHROPODS, 18);
        registerEnchantmentWithWrap(Enchantment.KNOCKBACK, 19);
        registerEnchantmentWithWrap(Enchantment.FIRE_ASPECT, 20);
        registerEnchantmentWithWrap(Enchantment.LOOTING, 21);

        registerEnchantmentWithWrap(Enchantment.EFFICIENCY, 32);
        registerEnchantmentWithWrap(Enchantment.SILK_TOUCH, 33);
        registerEnchantmentWithWrap(Enchantment.UNBREAKING, 34);
        registerEnchantmentWithWrap(Enchantment.FORTUNE, 35);

        registerEnchantmentWithWrap(Enchantment.POWER, 48);
        registerEnchantmentWithWrap(Enchantment.PUNCH, 49);
        registerEnchantmentWithWrap(Enchantment.FLAME, 50);
        registerEnchantmentWithWrap(Enchantment.INFINITY, 51);

        registerEnchantmentWithWrap(Enchantment.LUCK_OF_THE_SEA, 61);
        registerEnchantmentWithWrap(Enchantment.LURE, 62);

        registerMaterialWithWrap(Material.STONE, new MaterialData(1,0));
        registerMaterialWithWrap(Material.LEATHER_HELMET, new MaterialData(298,0));
        registerMaterialWithWrap(Material.LEATHER_CHESTPLATE, new MaterialData(299,0));
        registerMaterialWithWrap(Material.LEATHER_LEGGINGS, new MaterialData(301,0));
        registerMaterialWithWrap(Material.LEATHER_BOOTS, new MaterialData(302,0));
        registerItemStackRewriter(new LeatherClothItemStackRewriter(), Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);

    }

}
