package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutKeepAlive implements MinecraftPacketOut {

    private final int threshold;

    public MinecraftPacketOutKeepAlive(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(threshold);
    }
}
