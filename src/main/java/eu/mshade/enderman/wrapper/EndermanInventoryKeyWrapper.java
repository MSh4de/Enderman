package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.inventory.InventoryKey;
import eu.mshade.enderframe.inventory.InventoryType;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanInventoryKeyWrapper extends Wrapper<InventoryKey, String> {

    public EndermanInventoryKeyWrapper() {
        this.register(InventoryType.CHEST, NamespacedKey.minecraft("chest").toString());
        this.register(InventoryType.HOPPER, NamespacedKey.minecraft("hopper").toString());

    }
}
