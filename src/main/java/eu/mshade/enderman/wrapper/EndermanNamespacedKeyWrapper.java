package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanNamespacedKeyWrapper extends Wrapper<MaterialKey, NamespacedKey> {

    public EndermanNamespacedKeyWrapper() {

        this.registerOutput(Material.STONE, NamespacedKey.minecraft("stone"));

        this.registerInput(Material.STONE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.GRANITE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.POLISHED_GRANITE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.DIORITE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.POLISHED_DIORITE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.ANDESITE, NamespacedKey.minecraft("stone"));
        this.registerInput(Material.POLISHED_ANDESITE, NamespacedKey.minecraft("stone"));

        this.registerInput(Material.GRASS, NamespacedKey.minecraft("grass"));
    }
}
