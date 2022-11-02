package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.inventory.PlayerInventory;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutInventoryItems implements MinecraftPacketOut {

    private Inventory inventory;
    private int id;

    public MinecraftPacketOutInventoryItems(int id, Inventory inventory) {
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
