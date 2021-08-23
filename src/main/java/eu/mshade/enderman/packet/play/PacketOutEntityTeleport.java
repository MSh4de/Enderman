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
        byteMessage.writeByte((int) (entity.getLocation().getYaw() * 256.0F / 360.0F));
        byteMessage.writeByte((int) (entity.getLocation().getPitch() * 256.0F / 360.0F));
        byteMessage.writeBoolean(onGround);
    }

    private long d(double d0) {
        long i = (long) d0;

        return d0 < (double) i ? i - 1L : i;
    }

}
