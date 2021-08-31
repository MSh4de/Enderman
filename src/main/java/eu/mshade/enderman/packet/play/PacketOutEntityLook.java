package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityLook extends PacketOut {

    private final Entity entity;
    private final byte yaw,pitch;
    private final boolean isOnGround;

    public PacketOutEntityLook(Entity entity, byte yaw, byte pitch, boolean isOnGround) {
        this.entity = entity;
        this.yaw = yaw;
        this.pitch = pitch;
        this.isOnGround = isOnGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(isOnGround);
    }
}
