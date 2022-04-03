package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.mojang.SkinPart;

public class PlayerEntityMetadataBucket extends LivingEntityEntityMetadataBucket {

    public PlayerEntityMetadataBucket() {
        this.registerEntityMetadata(10, entity -> new ByteMetadata(entity.<SkinPart>getEntityMetadata(EntityMetadataType.SKIN_PART).get().toByte()));
        this.registerEntityMetadata(17, entity -> new FloatMetadata(entity.<Float>getEntityMetadata(EntityMetadataType.ADDITIONAL_HEARTS).get()));
        this.registerEntityMetadata(18, entity -> new IntegerMetadata(entity.<Integer>getEntityMetadata(EntityMetadataType.SCORE).get()));
        /*
        this.getMetadataRepository().registerMetadataIndex(10, MetadataType.BYTE, EntityMetadataType.SKIN_PART);
        this.getMetadataRepository().registerMetadataIndex(17, MetadataType.FLOAT, EntityMetadataType.ABSORPTION_HEARTS);
        this.getMetadataRepository().registerMetadataIndex(18, MetadataType.INTEGER, EntityMetadataType.SCORE);
        
         */
    }
}
