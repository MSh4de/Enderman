package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutPlayerPositionAndLook implements MinecraftPacketOut {

    private double x,y,z;
    private float yaw, pitch;

    public MinecraftPacketOutPlayerPositionAndLook(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeDouble(x);
        minecraftByteBuf.writeDouble(y);
        minecraftByteBuf.writeDouble(z);
        minecraftByteBuf.writeFloat(yaw);
        minecraftByteBuf.writeFloat(pitch);
        minecraftByteBuf.writeByte(0);
    }


}
