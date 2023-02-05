package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutSetItemStack implements MinecraftPacketOut {

    private final int slot;
    private int id;
    private final ItemStack itemStack;

    public MinecraftPacketOutSetItemStack(int slot, int id, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.id = id;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeByte(id);
        minecraftByteBuf.writeShort(slot);
        minecraftByteBuf.writeItemStack(itemStack);
    }
}
