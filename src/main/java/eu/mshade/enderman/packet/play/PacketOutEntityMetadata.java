package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityMetadata extends PacketOut {

    private final Entity entity;
    private final EntityMetadataType[] entityMetadataTypes;

    public PacketOutEntityMetadata(Entity entity, EntityMetadataType... entityMetadataTypes) {
        this.entity = entity;
        this.entityMetadataTypes = entityMetadataTypes;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        for (EntityMetadataType entityMetadataType : entityMetadataTypes) byteMessage.writeEntityMetadata(entity, entityMetadataType);
        byteMessage.writeByte(127);
    }
}
