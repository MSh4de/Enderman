package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;

public class LivingEntityMetadataBucket extends DefaultEntityMetadataBucket {

    public LivingEntityMetadataBucket() {
        this.registerEntityMetadata(6, entity -> new FloatMetadata(entity.<Float>getEntityMetadata(EntityMetadataType.HEALTH).get()), EntityMetadataType.HEALTH);
        this.registerEntityMetadata(7, entity -> new IntegerMetadata(entity.<Integer>getEntityMetadata(EntityMetadataType.POTION_EFFECT_COLOR).get()), EntityMetadataType.POTION_EFFECT_COLOR);
        this.registerEntityMetadata(8, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.POTION_EFFECT_AMBIENT).get()), EntityMetadataType.POTION_EFFECT_AMBIENT);
        this.registerEntityMetadata(9, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.NUMBER_OF_ARROWS_IN_ENTITY).get()), EntityMetadataType.NUMBER_OF_ARROWS_IN_ENTITY);
        this.registerEntityMetadata(15, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.WHETHER_ARTIFICIAL_INTELLIGENCE).get()),EntityMetadataType.WHETHER_ARTIFICIAL_INTELLIGENCE);
        /*
        this.getMetadataRepository().registerMetadataIndex(6, MetadataType.FLOAT, EntityMetadataType.HEALTH);
        this.getMetadataRepository().registerMetadataIndex(7, MetadataType.INTEGER, EntityMetadataType.POTION_EFFECT_COLOR);
        this.getMetadataRepository().registerMetadataIndex(8, MetadataType.BYTE, EntityMetadataType.IS_POTION_EFFECT_AMBIENT);
        this.getMetadataRepository().registerMetadataIndex(9, MetadataType.BYTE, EntityMetadataType.NUMBER_OF_ARROWS_IN_ENTITY);
        this.getMetadataRepository().registerMetadataIndex(15, MetadataType.BYTE, EntityMetadataType.WHETHER_AI);

         */
    }
}
