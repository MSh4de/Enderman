package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Location;

public class PacketOutEntityLookRelativeMove extends PacketOut  {

    private final Entity entity;
    private final byte x,y,z;
    private final int yaw,pitch;

    public PacketOutEntityLookRelativeMove(Entity entity, byte x, byte y, byte z, int yaw, int pitch) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte(x);
        byteMessage.writeByte(y);
        byteMessage.writeByte(z);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(false);
    }
}
