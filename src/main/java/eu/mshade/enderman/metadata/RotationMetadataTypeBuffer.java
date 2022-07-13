package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.MetadataBuffer;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Rotation;
import io.netty.buffer.ByteBuf;

public class RotationMetadataTypeBuffer implements MetadataBuffer<RotationMetadata> {
    @Override
    public void write(ProtocolBuffer protocolBuffer, RotationMetadata rotationMetadata) {
        Rotation rotation = rotationMetadata.get();
        protocolBuffer.writeFloat(rotation.getYaw());
        protocolBuffer.writeFloat(rotation.getPitch());
        protocolBuffer.writeFloat(rotation.getRoll());
    }
}
