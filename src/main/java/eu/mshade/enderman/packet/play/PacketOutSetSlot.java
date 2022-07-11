package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutSetSlot implements PacketOut {
    private final int slot;
    //Inventory
    private final ItemStack itemStack;

    public PacketOutSetSlot(int slot, ItemStack itemStack) {
        this.slot = slot;
        this.itemStack = itemStack;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(0);
        protocolBuffer.writeShort(slot);

        protocolBuffer.writeItemStack(itemStack);

//        protocolBuffer.writeShort(298);
//        protocolBuffer.writeByte(1);
//        protocolBuffer.writeShort(0);
//        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
//
//        CompoundBinaryTag displayBinaryTag = new CompoundBinaryTag();
//        displayBinaryTag.putInt("color", Color.LIME.asRGB());
//        displayBinaryTag.putString("Name", "Newz est gay");
//        compoundBinaryTag.putBinaryTag("display", displayBinaryTag);
//
//        protocolBuffer.writeCompoundTag(compoundBinaryTag);

    }
}
