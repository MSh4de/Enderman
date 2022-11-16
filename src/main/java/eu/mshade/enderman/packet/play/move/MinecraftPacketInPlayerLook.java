package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInPlayerLook extends MinecraftPacketInPlayerGround {

    private MinecraftSession minecraftSession;
    private float yaw, pith;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.yaw = minecraftByteBuf.readFloat() % 360;
        this.pith = minecraftByteBuf.readFloat() % 360;
        super.deserialize(minecraftSession, minecraftByteBuf);
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pith;
    }

    @Override
    public String toString() {
        return "PacketInPlayerLook{" +
                "yaw=" + yaw +
                ", pith=" + pith +
                ", onGround="+ this.isOnGround() +
                '}';
    }
}
