package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutWindowItems implements PacketOut {

    private Inventory inventory;
    private int id;

    public PacketOutWindowItems(int id, Inventory inventory) {
        this.inventory = inventory;
        this.id = id;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(id);
        protocolBuffer.writeShort(inventory.getItemStacks().length);
        for (ItemStack itemStack : inventory.getItemStacks()) {
            protocolBuffer.writeItemStack(itemStack);
        }
    }
}
