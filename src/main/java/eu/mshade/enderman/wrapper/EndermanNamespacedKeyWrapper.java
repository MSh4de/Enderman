package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanNamespacedKeyWrapper extends Wrapper<MaterialKey, NamespacedKey> {

    public EndermanNamespacedKeyWrapper() {

        this.registerMapping(Material.STONE, NamespacedKey.minecraft("stone"));

        this.registerReverseMapping(Material.STONE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.GRANITE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.POLISHED_GRANITE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.DIORITE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.POLISHED_DIORITE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.ANDESITE, NamespacedKey.minecraft("stone"));
        this.registerReverseMapping(Material.POLISHED_ANDESITE, NamespacedKey.minecraft("stone"));

        this.registerReverseMapping(Material.GRASS_BLOCK, NamespacedKey.minecraft("grass"));
    }
}
