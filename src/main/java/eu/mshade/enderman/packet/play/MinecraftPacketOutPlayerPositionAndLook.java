package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

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
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeDouble(x);
        protocolBuffer.writeDouble(y);
        protocolBuffer.writeDouble(z);
        protocolBuffer.writeFloat(yaw);
        protocolBuffer.writeFloat(pitch);
        protocolBuffer.writeByte(0);
    }


}