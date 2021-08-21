package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;

public class ZombieMetadataBuffer extends LivingEntityMetadataBuffer {

    public ZombieMetadataBuffer() {
        getMetadataRepository().registerMetadataIndex(12, MetadataType.BYTE, MetadataMeaning.IS_CHILD);
        getMetadataRepository().registerMetadataIndex(13, MetadataType.BYTE, MetadataMeaning.IS_VILLAGER);
        getMetadataRepository().registerMetadataIndex(14, MetadataType.BYTE, MetadataMeaning.IS_CONVERTING);
    }
}
