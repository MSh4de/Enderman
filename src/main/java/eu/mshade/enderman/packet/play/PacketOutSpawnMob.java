package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSpawnMob extends PacketOut {

    private int eid;
    private int type;
    private int x,y,z;
    private int yaw, pitch, angle;

    public PacketOutSpawnMob(int eid, int type, int x, int y, int z, int yaw, int pitch, int angle) {
        this.eid = eid;
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.angle = angle;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(eid);
        byteMessage.writeByte(type);
        byteMessage.writeInt(x*32);
        byteMessage.writeInt(y*32);
        byteMessage.writeInt(z*32);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeByte(angle);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        byteMessage.writeByte(127);
    }
}
