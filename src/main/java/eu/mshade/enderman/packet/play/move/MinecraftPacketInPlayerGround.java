package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInPlayerGround implements MinecraftPacketIn {

    private boolean onGround;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.onGround = minecraftByteBuf.readBoolean();
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public MinecraftSession getMinecraftSession() {
        return minecraftSession;
    }


    @Override
    public String toString() {
        return "PacketInPlayerGround{" +
                "onGround=" + onGround +
                '}';
    }
}
