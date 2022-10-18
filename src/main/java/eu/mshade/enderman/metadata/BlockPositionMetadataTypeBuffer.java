package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Vector;

public class BlockPositionMetadataTypeBuffer implements MetadataBuffer<BlockPositionMetadata> {

    @Override
    public void write(ProtocolBuffer protocolBuffer, BlockPositionMetadata blockPositionMetadata) {
        Vector blockPosition = blockPositionMetadata.get();
        protocolBuffer.writeInt(blockPosition.getBlockX());
        protocolBuffer.writeInt(blockPosition.getBlockY());
        protocolBuffer.writeInt(blockPosition.getBlockZ());
    }
}
