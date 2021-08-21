package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;

public class AbsorptionHeartsMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.FLOAT).write(byteMessage, new FloatMetadata(((Player) entity).getAbsorptionHearts()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.FLOAT;
    }
}
