package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.StringMetadata;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class CustomNameMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.STRING).write(byteMessage, new StringMetadata(entity.getCustomName()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.STRING;
    }
}
