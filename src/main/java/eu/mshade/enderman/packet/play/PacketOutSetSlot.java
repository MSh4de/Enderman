package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class PacketOutSetSlot extends PacketOut {
    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeByte(0);
        byteMessage.writeShort(36);

        byteMessage.writeShort(2);
        byteMessage.writeByte(1);
        byteMessage.writeShort(0);
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();

        CompoundBinaryTag displayBinaryTag = new CompoundBinaryTag();
        displayBinaryTag.putString("Name", "{\"text\":\"hey\"}");
        compoundBinaryTag.putBinaryTag("display", displayBinaryTag);

        byteMessage.writeCompoundTag(compoundBinaryTag);

    }
}
