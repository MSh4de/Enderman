package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;

public class ZombieMetadataBucket extends LivingEntityMetadataBucket {

    public ZombieMetadataBucket() {
        this.registerEntityMetadata(12, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.CHILD).get()), EntityMetadataType.CHILD);
        this.registerEntityMetadata(13, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.VILLAGER).get()), EntityMetadataType.VILLAGER);
        this.registerEntityMetadata(14, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.CONVERTING).get()), EntityMetadataType.CONVERTING);
        /*
        getMetadataRepository().registerMetadataIndex(12, MetadataType.BYTE, EntityMetadataType.CHILD);
        getMetadataRepository().registerMetadataIndex(13, MetadataType.BYTE, EntityMetadataType.IS_VILLAGER);
        getMetadataRepository().registerMetadataIndex(14, MetadataType.BYTE, EntityMetadataType.IS_CONVERTING);

         */
    }
}
