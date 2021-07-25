package eu.mshade.enderman;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderman.metadata.EndermanMetadataManager;
import io.netty.buffer.ByteBuf;

public class EndermanByteMessage extends ByteMessage {
    private static final MetadataManager metadataManager = new EndermanMetadataManager();

    public EndermanByteMessage(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void writeMetadata(EntityType entityType, MetadataEntry metadataEntry) {
        metadataManager.write(this, entityType, metadataEntry);
    }
}
