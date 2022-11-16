package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInClickInventory implements MinecraftPacketIn {

    private int id, slot, button, mode;
    private short transactionId;
    private ItemStack itemStack;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        id = minecraftByteBuf.readByte();
        slot = minecraftByteBuf.readShort();

        button = minecraftByteBuf.readByte();
        transactionId = minecraftByteBuf.readShort();
        mode = minecraftByteBuf.readByte();

        itemStack = minecraftByteBuf.readItemStack();

    }

    public int getId() {
        return id;
    }

    public int getSlot() {
        return slot;
    }

    public int getButton() {
        return button;
    }

    public int getMode() {
        return mode;
    }

    public short getTransactionId() {
        return transactionId;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    @Override
    public String toString() {
        return "PacketInClickInventory{" +
                "id=" + id +
                ", slot=" + slot +
                ", button=" + button +
                ", mode=" + mode +
                ", transactionId=" + transactionId +
                '}';
    }
}
