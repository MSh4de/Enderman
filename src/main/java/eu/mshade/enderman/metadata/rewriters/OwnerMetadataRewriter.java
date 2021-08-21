package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Tameable;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.StringMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;

public class OwnerMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.STRING).write(byteMessage, new StringMetadata(((Tameable)entity).getOwner()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.STRING;
    }
}
