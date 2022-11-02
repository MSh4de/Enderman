package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInClickInventory implements MinecraftPacketIn {

    private int id, slot, button, mode;
    private short transactionId;
    private ItemStack itemStack;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        id = protocolBuffer.readByte();
        slot = protocolBuffer.readShort();

        button = protocolBuffer.readByte();
        transactionId = protocolBuffer.readShort();
        mode = protocolBuffer.readByte();

        itemStack = protocolBuffer.readItemStack();

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
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
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
