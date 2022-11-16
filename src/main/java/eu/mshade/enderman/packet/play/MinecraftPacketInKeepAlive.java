package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInKeepAlive implements MinecraftPacketIn {

    private int threshold;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        threshold = minecraftByteBuf.readVarInt();
    }

    public int getThreshold() {
        return threshold;
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    @Override
    public String toString() {
        return "PacketInKeepAlive{" +
                "threshold=" + threshold +
                '}';
    }
}
