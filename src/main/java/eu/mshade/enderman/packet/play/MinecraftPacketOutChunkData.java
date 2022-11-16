package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

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
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeInt(x);
        minecraftByteBuf.writeInt(z);
        minecraftByteBuf.writeBoolean(continuous);
        minecraftByteBuf.writeShort(section);
        minecraftByteBuf.writeByteArray(data);
    }
}
