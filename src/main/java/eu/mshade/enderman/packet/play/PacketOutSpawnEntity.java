package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutSpawnEntity implements PacketOut {
    private final int aInt;

    public PacketOutSpawnEntity(int aInt) {
        this.aInt = aInt;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(aInt);
    }

}
