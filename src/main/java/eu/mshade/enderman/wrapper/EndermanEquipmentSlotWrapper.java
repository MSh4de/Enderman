package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.inventory.EquipmentSlot;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanEquipmentSlotWrapper extends Wrapper<EquipmentSlot, String> {

    public EndermanEquipmentSlotWrapper() {
        this.register(EquipmentSlot.MAIN_HAND,"mainhand");
        this.register(EquipmentSlot.OFF_HAND,"offhand");
        this.register(EquipmentSlot.BOOTS,"feet");
        this.register(EquipmentSlot.LEGGINGS,"legs");
        this.register(EquipmentSlot.CHEST_PLATE,"torso");
        this.register(EquipmentSlot.HEAD,"head");
    }
}
