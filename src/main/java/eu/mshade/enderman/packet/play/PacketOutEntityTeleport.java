package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityTeleport extends PacketOut {

    private final Entity entity;
    private final boolean onGround;

    private final int x, y, z, yaw, pitch;

    public PacketOutEntityTeleport(Entity entity, int x, int y, int z, int yaw, int pitch, boolean onGround) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeInt(x);
        byteMessage.writeInt(y);
        byteMessage.writeInt(z);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(onGround);
    }

}
