package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutChunkData extends PacketOut {

    private int x, z;
    private boolean continuous;
    private int section;
    private byte[] data;

    public PacketOutChunkData(int x, int z, boolean continuous, int section, byte[] data) {
        this.x = x;
        this.z = z;
        this.continuous = continuous;
        this.section = section;
        this.data = data;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeInt(x);
        byteMessage.writeInt(z);
        byteMessage.writeBoolean(continuous);
        byteMessage.writeShort(section);
        byteMessage.writeByteArray(data);
    }
}
