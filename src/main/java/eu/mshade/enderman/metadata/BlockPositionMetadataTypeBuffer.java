package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.buffer.MetadataBuffer;
import eu.mshade.enderframe.metadata.buffer.type.MetadataTypeBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.BlockPosition;

public class BlockPositionMetadataTypeBuffer implements MetadataTypeBuffer {

    @Override
    public void write(ByteMessage byteMessage, Metadata<?> metadata) {
        BlockPosition blockPosition = (BlockPosition) metadata.get();
        byteMessage.writeInt(blockPosition.getX());
        byteMessage.writeInt(blockPosition.getY());
        byteMessage.writeInt(blockPosition.getZ());
    }
}
