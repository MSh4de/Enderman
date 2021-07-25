package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

import java.util.List;

public class PacketOutEntityMetadata extends PacketOut {

    private final int id;
    private final EntityType entityType;
    private final List<MetadataEntry> metadata;

    public PacketOutEntityMetadata(int id, EntityType entityType, List<MetadataEntry> metadata) {
        this.id = id;
        this.entityType = entityType;
        this.metadata = metadata;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        metadata.forEach(metadata -> byteMessage.writeMetadata(entityType,metadata));
        byteMessage.writeByte(127);
    }
}
