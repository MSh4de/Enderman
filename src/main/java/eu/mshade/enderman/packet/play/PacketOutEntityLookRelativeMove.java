package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityLookRelativeMove extends PacketOut  {

    private final int id;
    private final double x,y,z;
    private final byte yaw,pitch;
    private final boolean onGround;

    public PacketOutEntityLookRelativeMove(int id, double x, double y, double z, byte yaw, byte pitch, boolean onGround) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte((int) x);
        byteMessage.writeByte((int) y);
        byteMessage.writeByte((int) z);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(onGround);
    }
}
