package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Enderman;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ShortMetadata;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class CarriedBlockMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.SHORT).write(byteMessage, new ShortMetadata(((Enderman)entity).getCarriedBlock()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.SHORT;
    }
}
