package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.ByteMetadata;

public class ZombieMetadataBucket extends LivingEntityMetadataBucket {

    public ZombieMetadataBucket() {
        this.registerEntityMetadata(12, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.CHILD, Boolean.class)), EntityMetadataKey.CHILD);
        this.registerEntityMetadata(13, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.VILLAGER, Boolean.class)), EntityMetadataKey.VILLAGER);
        this.registerEntityMetadata(14, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.CONVERTING, Boolean.class)), EntityMetadataKey.CONVERTING);
    }
}
