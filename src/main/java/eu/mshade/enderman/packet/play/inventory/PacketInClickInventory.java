package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.ClickType;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInClickInventory implements PacketIn {

    private int id, slot, button, mode;
    private short transactionId;
    private ItemStack itemStack;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
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
}
