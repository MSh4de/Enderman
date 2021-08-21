package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutPlayerPositionAndLook extends PacketOut {

    private double x,y,z;
    private float yaw, pitch;

    public PacketOutPlayerPositionAndLook(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeDouble(x);
        byteMessage.writeDouble(y);
        byteMessage.writeDouble(z);
        byteMessage.writeFloat(yaw);
        byteMessage.writeFloat(pitch);
        byteMessage.writeByte(0);
    }


}
