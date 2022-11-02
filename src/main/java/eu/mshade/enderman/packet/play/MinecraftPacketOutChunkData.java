package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutChunkData implements MinecraftPacketOut {

    private int x, z;
    private boolean continuous;
    private int section;
    private byte[] data;

    public MinecraftPacketOutChunkData(int x, int z, boolean continuous, int section, byte[] data) {
        this.x = x;
        this.z = z;
        this.continuous = continuous;
        this.section = section;
        this.data = data;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeInt(x);
        protocolBuffer.writeInt(z);
        protocolBuffer.writeBoolean(continuous);
        protocolBuffer.writeShort(section);
        protocolBuffer.writeByteArray(data);
    }
}
