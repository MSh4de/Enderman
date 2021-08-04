package eu.mshade.enderman.metadata.v2.rewriters;

import eu.mshade.enderframe.entity.Creeper;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class CreeperStateMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.BYTE).write(byteMessage, new ByteMetadata((byte) ((Creeper)entity).getState().ordinal()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.BYTE;
    }
}
