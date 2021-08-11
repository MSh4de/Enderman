package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityTeleport extends PacketOut {
    private final Entity entity;
    private final boolean onGround;

    public PacketOutEntityTeleport(Entity entity, boolean onGround) {
        this.entity = entity;
        this.onGround = onGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeInt((int) (entity.getLocation().getX() *32));
        byteMessage.writeInt((int) (entity.getLocation().getY() *32));
        byteMessage.writeInt((int) (entity.getLocation().getZ() *32));
        byteMessage.writeByte((int) (entity.getLocation().getYaw() % 360 / 360 * 256));
        byteMessage.writeByte((int) (entity.getLocation().getPitch() % 360 / 360 * 256));
        byteMessage.writeBoolean(onGround);
    }

}
