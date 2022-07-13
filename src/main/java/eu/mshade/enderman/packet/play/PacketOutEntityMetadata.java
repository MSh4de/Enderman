package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutEntityMetadata implements PacketOut {

    private final Entity entity;
    private final EntityMetadataKey[] entityMetadataKeys;

    public PacketOutEntityMetadata(Entity entity, EntityMetadataKey... entityMetadataKeys) {
        this.entity = entity;
        this.entityMetadataKeys = entityMetadataKeys;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        for (EntityMetadataKey entityMetadataKey : entityMetadataKeys) protocolBuffer.writeEntityMetadata(entity, entityMetadataKey);
        protocolBuffer.writeByte(127);
    }
}
