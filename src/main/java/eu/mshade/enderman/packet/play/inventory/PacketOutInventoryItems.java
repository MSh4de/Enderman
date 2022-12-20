package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutInventoryItems implements PacketOut {

    private Inventory inventory;
    private int id;

    public PacketOutInventoryItems(int id, Inventory inventory) {
        this.inventory = inventory;
        this.id = id;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(id);
        int length = (inventory instanceof PlayerInventory ? 45 : inventory.getItemStacks().length);
        protocolBuffer.writeShort(length);
        for (int i = 0; i < length; i++) {
            protocolBuffer.writeItemStack(inventory.getItemStacks()[i]);
        }
    }
}
