package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;

public class LivingEntityMetadataBuffer extends EntityMetadataBuffer {

    public LivingEntityMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(6, MetadataType.FLOAT, MetadataMeaning.HEALTH);
        this.getMetadataRepository().registerMetadataIndex(7, MetadataType.INTEGER, MetadataMeaning.POTION_EFFECT_COLOR);
        this.getMetadataRepository().registerMetadataIndex(8, MetadataType.BYTE, MetadataMeaning.IS_POTION_EFFECT_AMBIENT);
        this.getMetadataRepository().registerMetadataIndex(9, MetadataType.BYTE, MetadataMeaning.NUMBER_OF_ARROWS_IN_ENTITY);
        this.getMetadataRepository().registerMetadataIndex(15, MetadataType.BYTE, MetadataMeaning.WHETHER_AI);
    }
}
