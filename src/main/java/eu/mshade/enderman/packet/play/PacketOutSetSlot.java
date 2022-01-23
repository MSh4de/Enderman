package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSetSlot extends PacketOut {
    private final int slot;
    //Inventory
    private final ItemStack itemStack;

    public PacketOutSetSlot(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeByte(0);
        byteMessage.writeShort(slot);

        byteMessage.writeItemStack(itemStack);

//        byteMessage.writeShort(298);
//        byteMessage.writeByte(1);
//        byteMessage.writeShort(0);
//        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
//
//        CompoundBinaryTag displayBinaryTag = new CompoundBinaryTag();
//        displayBinaryTag.putInt("color", Color.LIME.asRGB());
//        displayBinaryTag.putString("Name", "Newz est gay");
//        compoundBinaryTag.putBinaryTag("display", displayBinaryTag);
//
//        byteMessage.writeCompoundTag(compoundBinaryTag);

    }
}
