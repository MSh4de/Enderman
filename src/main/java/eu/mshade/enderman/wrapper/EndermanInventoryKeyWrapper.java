package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.inventory.InventoryKey;
import eu.mshade.enderframe.inventory.InventoryType;
import eu.mshade.enderframe.mojang.NamespacedKey;

public class EndermanInventoryKeyWrapper extends Wrapper<InventoryKey, String> {

    public EndermanInventoryKeyWrapper() {
        this.register(InventoryType.CHEST, NamespacedKey.minecraft("chest").toString());
        this.register(InventoryType.HOPPER, NamespacedKey.minecraft("hopper").toString());
        this.register(InventoryType.FURNACE, NamespacedKey.minecraft("furnace").toString());
        this.register(InventoryType.WORKBENCH, NamespacedKey.minecraft("crafting_table").toString());
        this.register(InventoryType.DISPENSER, NamespacedKey.minecraft("dispenser").toString());
        this.register(InventoryType.ENCHANTMENT_TABLE, NamespacedKey.minecraft("enchanting_table").toString());
        this.register(InventoryType.BREWING_STAND, NamespacedKey.minecraft("brewing_stand").toString());
        this.register(InventoryType.VILLAGER, NamespacedKey.minecraft("villager").toString());
        this.register(InventoryType.BEACON, NamespacedKey.minecraft("beacon").toString());



    }
}
