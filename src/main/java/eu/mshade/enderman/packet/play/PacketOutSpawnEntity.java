package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSpawnEntity extends PacketOut {
    private final int aInt;

    public PacketOutSpawnEntity(int aInt) {
        this.aInt = aInt;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(aInt);
    }

}
