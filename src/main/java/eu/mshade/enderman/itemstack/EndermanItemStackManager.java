package eu.mshade.enderman.itemstack;

import eu.mshade.enderframe.item.EnchantmentType;
import eu.mshade.enderframe.item.ItemStackManager;
import eu.mshade.enderframe.item.MaterialData;
import eu.mshade.enderman.itemstack.rewriters.CommonItemStackRewriter;

public class EndermanItemStackManager extends ItemStackManager<MaterialData, Integer> {

    public EndermanItemStackManager() {
        super(new CommonItemStackRewriter());
        /*
        registerNamespacedKey(NamespacedKey.minecraft("wool"), Material.WHITE_WOOL, Material.BLACK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL,
                Material.BROWN_WOOL, Material.ORANGE_WOOL, Material.YELLOW_WOOL, Material.RED_WOOL, Material.LIME_WOOL, Material.GREEN_WOOL, Material.CYAN_WOOL,
                Material.BLUE_WOOL, Material.LIGHT_BLUE_WOOL, Material.PINK_WOOL, Material.PURPLE_WOOL, Material.MAGENTA_WOOL);

         */

        registerEnchantmentWithWrap(EnchantmentType.PROTECTION, 0);
        registerEnchantmentWithWrap(EnchantmentType.FIRE_PROTECTION, 1);
        registerEnchantmentWithWrap(EnchantmentType.FEATHER_FALLING, 2);
        registerEnchantmentWithWrap(EnchantmentType.BLAST_PROTECTION, 3);
        registerEnchantmentWithWrap(EnchantmentType.PROJECTILE_PROTECTION, 4);
        registerEnchantmentWithWrap(EnchantmentType.RESPIRATION, 5);
        registerEnchantmentWithWrap(EnchantmentType.AQUA_AFFINITY, 6);
        registerEnchantmentWithWrap(EnchantmentType.THORNS, 7);
        registerEnchantmentWithWrap(EnchantmentType.DEPTH_STRIDER, 8);

        registerEnchantmentWithWrap(EnchantmentType.SHARPNESS, 16);
        registerEnchantmentWithWrap(EnchantmentType.SMITE, 17);
        registerEnchantmentWithWrap(EnchantmentType.BANE_OF_ARTHROPODS, 18);
        registerEnchantmentWithWrap(EnchantmentType.KNOCKBACK, 19);
        registerEnchantmentWithWrap(EnchantmentType.FIRE_ASPECT, 20);
        registerEnchantmentWithWrap(EnchantmentType.LOOTING, 21);

        registerEnchantmentWithWrap(EnchantmentType.EFFICIENCY, 32);
        registerEnchantmentWithWrap(EnchantmentType.SILK_TOUCH, 33);
        registerEnchantmentWithWrap(EnchantmentType.UNBREAKING, 34);
        registerEnchantmentWithWrap(EnchantmentType.FORTUNE, 35);

        registerEnchantmentWithWrap(EnchantmentType.POWER, 48);
        registerEnchantmentWithWrap(EnchantmentType.PUNCH, 49);
        registerEnchantmentWithWrap(EnchantmentType.FLAME, 50);
        registerEnchantmentWithWrap(EnchantmentType.INFINITY, 51);

        registerEnchantmentWithWrap(EnchantmentType.LUCK_OF_THE_SEA, 61);
        registerEnchantmentWithWrap(EnchantmentType.LURE, 62);

        //registerMaterialWithWrap(Material.STONE, new MaterialData(1,0));
        /*
        registerMaterialWithWrap(Material.LEATHER_HELMET, new MaterialData(298,0));
        registerMaterialWithWrap(Material.LEATHER_CHESTPLATE, new MaterialData(299,0));
        registerMaterialWithWrap(Material.LEATHER_LEGGINGS, new MaterialData(300,0));
        registerMaterialWithWrap(Material.LEATHER_BOOTS, new MaterialData(301,0));
        //registerMaterialWithWrap(Material.BLACK_BANNER, new MaterialData(425,0));
        registerMaterialWithWrap(Material.MAGENTA_BANNER, new MaterialData(425,0));
        registerItemStackRewriter(new LeatherArmorItemStackRewriter(), Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);

         */

    }

}
