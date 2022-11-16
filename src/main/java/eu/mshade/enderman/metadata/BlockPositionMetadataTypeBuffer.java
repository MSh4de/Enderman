package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.BlockPositionMetadata;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Vector;

public class BlockPositionMetadataTypeBuffer implements MetadataBuffer<BlockPositionMetadata> {

    @Override
    public void write(MinecraftByteBuf minecraftByteBuf, BlockPositionMetadata blockPositionMetadata) {
        Vector blockPosition = blockPositionMetadata.get();
        minecraftByteBuf.writeInt(blockPosition.getBlockX());
        minecraftByteBuf.writeInt(blockPosition.getBlockY());
        minecraftByteBuf.writeInt(blockPosition.getBlockZ());
    }
}
