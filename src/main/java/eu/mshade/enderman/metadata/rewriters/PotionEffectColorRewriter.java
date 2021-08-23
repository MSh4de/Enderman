package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.LivingEntity;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;

public class PotionEffectColorRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        metadataManager.getMetadataTypeBuffer(MetadataType.INTEGER).write(byteMessage, new IntegerMetadata(((LivingEntity)entity).getPotionEffectColor()));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.INTEGER;
    }
}