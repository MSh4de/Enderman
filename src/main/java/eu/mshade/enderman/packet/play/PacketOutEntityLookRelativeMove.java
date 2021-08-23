package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Location;

public class PacketOutEntityLookRelativeMove extends PacketOut  {

    private final int id;
    private final byte x,y,z;
    private final int yaw,pitch;
    private final boolean onGround;

    public PacketOutEntityLookRelativeMove(int id, byte x, byte y, byte z, int yaw, int pitch, boolean onGround) {
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
        byteMessage.writeByte(x);
        byteMessage.writeByte(y);
        byteMessage.writeByte(z);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(onGround);
    }
}
