package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutWindowItems implements PacketOut {

    private Inventory inventory;

    public PacketOutWindowItems(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(inventory.getId());
        protocolBuffer.writeShort(inventory.getItemStacks().length);
        for (ItemStack itemStack : inventory.getItemStacks()) {
            protocolBuffer.writeItemStack(itemStack);
        }
    }
}
