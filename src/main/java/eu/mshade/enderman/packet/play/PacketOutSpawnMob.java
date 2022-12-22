package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataKey;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutSpawnMob implements PacketOut {
    private final int id;
    private final Entity entity;

    public PacketOutSpawnMob(int id, Entity entity) {
        this.id = id;
        this.entity = entity;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        protocolBuffer.writeByte(id);
        protocolBuffer.writeInt(entity.getLocation().getBlockX() * 32);
        protocolBuffer.writeInt(entity.getLocation().getBlockY() * 32);
        protocolBuffer.writeInt(entity.getLocation().getBlockZ() * 32);
        protocolBuffer.writeByte((byte) (entity.getLocation().getYaw() * 256 / 360));
        protocolBuffer.writeByte((byte) (entity.getLocation().getPitch() * 256 / 360));
        protocolBuffer.writeByte((byte) (entity.getLocation().getYaw() * 256 / 360));
        protocolBuffer.writeShort((int) entity.getVelocity().getX());
        protocolBuffer.writeShort((int) entity.getVelocity().getY());
        protocolBuffer.writeShort((int) entity.getVelocity().getZ());
        for (MetadataKey entityMetadataKey : protocolBuffer.getSupportedMetadataKeys(entity)) {
            protocolBuffer.writeEntityMetadata(entity, entityMetadataKey);
        }
        protocolBuffer.writeByte(0x7F);
    }

}
