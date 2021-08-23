package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Rabbit;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class RabbitTypeMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.BYTE).write(byteMessage,new ByteMetadata(((Rabbit)entity).getRabbitType()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.BYTE;
    }
}