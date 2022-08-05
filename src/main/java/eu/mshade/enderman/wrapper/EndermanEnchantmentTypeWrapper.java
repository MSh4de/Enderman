package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.EnchantmentType;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanEnchantmentTypeWrapper extends Wrapper<EnchantmentType, Integer> {

    public EndermanEnchantmentTypeWrapper() {
        register(EnchantmentType.PROTECTION, 0);
        register(EnchantmentType.FIRE_PROTECTION, 1);
        register(EnchantmentType.FEATHER_FALLING, 2);
        register(EnchantmentType.BLAST_PROTECTION, 3);
        register(EnchantmentType.PROJECTILE_PROTECTION, 4);
        register(EnchantmentType.RESPIRATION, 5);
        register(EnchantmentType.AQUA_AFFINITY, 6);
        register(EnchantmentType.THORNS, 7);
        register(EnchantmentType.DEPTH_STRIDER, 8);

        register(EnchantmentType.SHARPNESS, 16);
        register(EnchantmentType.SMITE, 17);
        register(EnchantmentType.BANE_OF_ARTHROPODS, 18);
        register(EnchantmentType.KNOCKBACK, 19);
        register(EnchantmentType.FIRE_ASPECT, 20);
        register(EnchantmentType.LOOTING, 21);

        register(EnchantmentType.EFFICIENCY, 32);
        register(EnchantmentType.SILK_TOUCH, 33);
        register(EnchantmentType.UNBREAKING, 34);
        register(EnchantmentType.FORTUNE, 35);

        register(EnchantmentType.POWER, 48);
        register(EnchantmentType.PUNCH, 49);
        register(EnchantmentType.FLAME, 50);
        register(EnchantmentType.INFINITY, 51);

        register(EnchantmentType.LUCK_OF_THE_SEA, 61);
        register(EnchantmentType.LURE, 62);


    }
}
