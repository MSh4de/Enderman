package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.inventory.PlayerInventory;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutInventoryItems implements MinecraftPacketOut {

    private Inventory inventory;
    private int id;

    public MinecraftPacketOutInventoryItems(int id, Inventory inventory) {
        this.inventory = inventory;
        this.id = id;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeByte(id);
        int length = (inventory instanceof PlayerInventory ? 45 : inventory.itemStacks.length);
        minecraftByteBuf.writeShort(length);
        for (int i = 0; i < length; i++) {
            minecraftByteBuf.writeItemStack(inventory.itemStacks[i]);
        }
    }
}
