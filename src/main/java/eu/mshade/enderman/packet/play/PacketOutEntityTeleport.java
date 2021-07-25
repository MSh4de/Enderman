package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityTeleport extends PacketOut {
    private final int id;
    private final int x,y,z;
    private final byte yaw,pitch;
    private final boolean onGround;

    public PacketOutEntityTeleport(int id, int x, int y, int z, byte yaw, byte pitch, boolean onGround) {
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
        byteMessage.writeInt(x);
        byteMessage.writeInt(y);
        byteMessage.writeInt(z);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(onGround);
    }
}
