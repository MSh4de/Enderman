package eu.mshade.enderman.metadata.v2.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Horse;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class HorseColorMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.INTEGER).write(byteMessage, new IntegerMetadata(((Horse)entity).getHorseColor().ordinal()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.INTEGER;
    }
}
