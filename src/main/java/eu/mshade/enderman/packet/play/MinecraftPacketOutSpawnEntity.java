package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutSpawnEntity implements MinecraftPacketOut {
    private final int aInt;

    public MinecraftPacketOutSpawnEntity(int aInt) {
        this.aInt = aInt;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(aInt);
    }

}
