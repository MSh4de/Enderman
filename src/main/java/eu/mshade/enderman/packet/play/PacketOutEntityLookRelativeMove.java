package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Location;

public class PacketOutEntityLookRelativeMove extends PacketOut  {

    private final int id;
    private final byte x,y,z;
    private final int yaw,pitch;
    private final boolean onGround;

    public PacketOutEntityLookRelativeMove(int id, Location now, Location before, boolean onGround) {
        this.id = id;
        this.x = (byte) (floor(now.getX() * 32) - floor(before.getX() * 32));
        this.y = (byte) (floor(now.getY() * 32) - floor(before.getY() * 32));
        this.z = (byte) (floor(now.getZ() * 32) - floor(before.getZ() * 32));
        this.yaw = (int) (now.getYaw() % 360 / 360 * 256);
        this.pitch = (int) (now.getPitch() % 360 / 360 * 256);
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

    public int floor(double d0) {
        int i = (int) d0;

        return d0 < (double) i ? i - 1 : i;
    }
}
