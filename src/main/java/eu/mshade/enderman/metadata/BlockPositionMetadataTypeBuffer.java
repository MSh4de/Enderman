package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.BlockPosition;

public class BlockPositionMetadataTypeBuffer implements MetadataBuffer<BlockPositionMetadata> {

    @Override
    public void write(ByteMessage byteMessage, BlockPositionMetadata blockPositionMetadata) {
        BlockPosition blockPosition = blockPositionMetadata.get();
        byteMessage.writeInt(blockPosition.getX());
        byteMessage.writeInt(blockPosition.getY());
        byteMessage.writeInt(blockPosition.getZ());
    }
}
