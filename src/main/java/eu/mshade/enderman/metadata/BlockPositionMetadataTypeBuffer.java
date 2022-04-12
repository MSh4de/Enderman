package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.BlockPosition;
import io.netty.buffer.ByteBuf;

public class BlockPositionMetadataTypeBuffer implements MetadataBuffer<BlockPositionMetadata> {

    @Override
    public void write(ByteBuf byteBuf, BlockPositionMetadata blockPositionMetadata) {
        BlockPosition blockPosition = blockPositionMetadata.get();
        byteBuf.writeInt(blockPosition.getX());
        byteBuf.writeInt(blockPosition.getY());
        byteBuf.writeInt(blockPosition.getZ());
    }
}
