package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.buffer.type.MetadataTypeBuffer;
import eu.mshade.enderframe.protocol.ByteMessage;

public class StringMetadataTypeBuffer implements MetadataTypeBuffer {

    @Override
    public void write(ByteMessage byteMessage, Metadata<?> metadata) {
        byteMessage.writeString((CharSequence) metadata.get());
    }
}
