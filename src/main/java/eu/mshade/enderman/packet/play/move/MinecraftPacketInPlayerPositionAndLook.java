package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInPlayerPositionAndLook extends MinecraftPacketInPlayerLook {

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
    public MinecraftSession getMinecraftSession() {
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
        return "PacketInPlayerPositionAndLook{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw="+ this.getYaw()+
                ", pitch="+ this.getPitch()+
                ", onGround="+this.isOnGround() +
                '}';
    }
}
