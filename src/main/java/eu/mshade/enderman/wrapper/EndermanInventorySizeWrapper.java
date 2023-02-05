package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.inventory.InventoryKey;
import eu.mshade.enderframe.inventory.InventoryType;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanInventorySizeWrapper extends Wrapper<InventoryKey, Integer> {

    public EndermanInventorySizeWrapper() {
        this.registerMapping(InventoryType.WORKBENCH, 9);
        this.registerMapping(InventoryType.CHEST, 9*3);
        this.registerMapping(InventoryType.HOPPER, 5);
        this.registerMapping(InventoryType.PLAYER, 45);
        this.registerMapping(InventoryType.FURNACE, 3);

    }
}
