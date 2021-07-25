package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;

public class EnderCrystalMetadataBuffer extends EntityMetadataBuffer {

    public EnderCrystalMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(8, MetadataType.INTEGER, MetadataMeaning.HEALTH);
    }
}
