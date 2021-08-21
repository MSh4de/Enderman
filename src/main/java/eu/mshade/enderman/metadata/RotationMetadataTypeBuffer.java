package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.buffer.type.MetadataTypeBuffer;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.world.Rotation;

public class RotationMetadataTypeBuffer implements MetadataTypeBuffer {

    @Override
    public void write(ByteMessage byteMessage, Metadata<?> metadata) {
         Rotation rotation = (Rotation) metadata.get();

         byteMessage.writeFloat(rotation.getYaw());
         byteMessage.writeFloat(rotation.getPitch());
         byteMessage.writeFloat(rotation.getRoll());
    }
}
