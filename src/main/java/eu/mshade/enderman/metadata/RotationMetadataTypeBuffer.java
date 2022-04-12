package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.Rotation;
import io.netty.buffer.ByteBuf;

public class RotationMetadataTypeBuffer implements MetadataBuffer<RotationMetadata> {
    @Override
    public void write(ByteBuf byteBuf, RotationMetadata rotationMetadata) {
        Rotation rotation = rotationMetadata.get();
        byteBuf.writeFloat(rotation.getYaw());
        byteBuf.writeFloat(rotation.getPitch());
        byteBuf.writeFloat(rotation.getRoll());
    }
}
