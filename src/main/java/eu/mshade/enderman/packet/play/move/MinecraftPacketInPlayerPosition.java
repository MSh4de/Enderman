package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInPlayerPosition extends MinecraftPacketInPlayerGround {

    private MinecraftSession minecraftSession;
    private double x, y, z;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.x = minecraftByteBuf.readDouble();
        this.y = minecraftByteBuf.readDouble();
        this.z = minecraftByteBuf.readDouble();
        super.deserialize(minecraftSession, minecraftByteBuf);
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    @Override
    public String toString() {
        return "PacketInPlayerPosition{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", onGround="+ this.isOnGround() +
                '}';
    }
}
