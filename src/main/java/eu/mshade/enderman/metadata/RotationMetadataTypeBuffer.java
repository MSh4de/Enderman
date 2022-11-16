package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Rotation;

public class RotationMetadataTypeBuffer implements MetadataBuffer<RotationMetadata> {
    @Override
    public void write(MinecraftByteBuf minecraftByteBuf, RotationMetadata rotationMetadata) {
        Rotation rotation = rotationMetadata.get();
        minecraftByteBuf.writeFloat(rotation.getYaw());
        minecraftByteBuf.writeFloat(rotation.getPitch());
        minecraftByteBuf.writeFloat(rotation.getRoll());
    }
}
