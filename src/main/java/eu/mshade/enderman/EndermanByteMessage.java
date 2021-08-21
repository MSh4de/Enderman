package eu.mshade.enderman;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderman.metadata.EndermanMetadataManager;
import io.netty.buffer.ByteBuf;

public class EndermanByteMessage extends ByteMessage {

    private static final MetadataManager metadataManager = new EndermanMetadataManager();

    public EndermanByteMessage(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void writeMetadata(Entity entity, MetadataMeaning metadataMeaning) {
        metadataManager.write(this, entity, metadataMeaning);
    }
}
