package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataKey;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutEntityMetadata implements PacketOut {

    private final Entity entity;
    private final MetadataKey[] metadataKeys;

    public PacketOutEntityMetadata(Entity entity, MetadataKey... metadataKeys) {
        this.entity = entity;
        this.metadataKeys = metadataKeys;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        for (MetadataKey entityMetadataKey : metadataKeys) {
            protocolBuffer.writeEntityMetadata(entity, entityMetadataKey);
        }
        protocolBuffer.writeByte(127);
    }
}
