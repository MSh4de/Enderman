package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;

public class PlayerMetadataBuffer extends LivingEntityMetadataBuffer{

    public PlayerMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(10, MetadataType.BYTE, MetadataMeaning.SKIN_PART);
        this.getMetadataRepository().registerMetadataIndex(16, MetadataType.BYTE, MetadataMeaning.UNUSED);
        this.getMetadataRepository().registerMetadataIndex(17, MetadataType.FLOAT, MetadataMeaning.ABSORPTION_HEARTS);
        this.getMetadataRepository().registerMetadataIndex(18, MetadataType.INTEGER, MetadataMeaning.SCORE);
    }
}
