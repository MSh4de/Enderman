package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderman.wrapper.EndermanInventoryKeyWrapper;

public class PacketOutOpenInventory implements PacketOut {

    protected Inventory inventory;
    protected EndermanInventoryKeyWrapper endermanInventoryKeyWrapper;

    public PacketOutOpenInventory(EndermanInventoryKeyWrapper endermanInventoryKeyWrapper, Inventory inventory) {
        this.endermanInventoryKeyWrapper = endermanInventoryKeyWrapper;
        this.inventory = inventory;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(inventory.getId());
        protocolBuffer.writeString(endermanInventoryKeyWrapper.wrap(inventory.getInventoryKey()));
        protocolBuffer.writeValueAsString(inventory.getTextComponent());
        protocolBuffer.writeByte(inventory.getInventoryKey().getDefaultSlot());


    }
}
