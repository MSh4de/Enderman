package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanInventorySizeWrapper extends Wrapper<InventoryKey, Integer> {

    public EndermanInventorySizeWrapper() {
        this.registerInput(InventoryType.WORKBENCH, 9);
        this.registerInput(InventoryType.CHEST, 9*3);
        this.registerInput(InventoryType.HOPPER, 5);
        this.registerInput(InventoryType.PLAYER, 45);
        this.registerInput(InventoryType.FURNACE, 3);

    }
}
