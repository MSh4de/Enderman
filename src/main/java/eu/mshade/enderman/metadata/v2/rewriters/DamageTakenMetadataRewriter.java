package eu.mshade.enderman.metadata.v2.rewriters;

import eu.mshade.enderframe.entity.Damageable;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class DamageTakenMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.FLOAT).write(byteMessage, new FloatMetadata(((Damageable)entity).getDamageTaken()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.FLOAT;
    }
}
