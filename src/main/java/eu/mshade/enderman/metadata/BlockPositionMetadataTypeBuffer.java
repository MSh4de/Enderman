package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.BlockPosition;

public class BlockPositionMetadataTypeBuffer implements MetadataBuffer<BlockPositionMetadata> {

    @Override
    public void write(ProtocolBuffer protocolBuffer, BlockPositionMetadata blockPositionMetadata) {
        BlockPosition blockPosition = blockPositionMetadata.get();
        protocolBuffer.writeInt(blockPosition.getX());
        protocolBuffer.writeInt(blockPosition.getY());
        protocolBuffer.writeInt(blockPosition.getZ());
    }
}
