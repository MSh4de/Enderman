package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSpawnMob extends PacketOut {

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(1);
        byteMessage.writeByte(54);
        byteMessage.writeInt(10);
        byteMessage.writeInt(52);
        byteMessage.writeInt(10);
        byteMessage.writeByte(1);
        byteMessage.writeByte(1);
        byteMessage.writeByte(1);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        byteMessage.writeByte(127);
    }
}
