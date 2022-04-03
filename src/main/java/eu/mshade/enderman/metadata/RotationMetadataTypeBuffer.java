package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.Rotation;

public class RotationMetadataTypeBuffer implements MetadataBuffer<RotationMetadata> {
    @Override
    public void write(ByteMessage byteMessage, RotationMetadata rotationMetadata) {
        Rotation rotation = rotationMetadata.get();
        byteMessage.writeFloat(rotation.getYaw());
        byteMessage.writeFloat(rotation.getPitch());
        byteMessage.writeFloat(rotation.getRoll());
    }
}
