package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSpawnMob extends PacketOut {

    private final int id;
    private final Entity entity;

    public PacketOutSpawnMob(int id, Entity entity) {
        this.id = id;
        this.entity = entity;
    }


    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte(id);
        byteMessage.writeInt(entity.getLocation().getBlockX() * 32);
        byteMessage.writeInt(entity.getLocation().getBlockY() * 32);
        byteMessage.writeInt(entity.getLocation().getBlockZ() * 32);
        byteMessage.writeByte((byte) (entity.getLocation().getYaw() % 360 / 360 * 256));
        byteMessage.writeByte((byte) (entity.getLocation().getPitch() % 360 / 360 * 256));
        byteMessage.writeByte((byte) (entity.getLocation().getPitch() % 360 / 360 * 256));
        byteMessage.writeShort((int) entity.getVelocity().getX());
        byteMessage.writeShort((int) entity.getVelocity().getY());
        byteMessage.writeShort((int) entity.getVelocity().getZ());
        byteMessage.writeByte(0x7F);
    }

}
