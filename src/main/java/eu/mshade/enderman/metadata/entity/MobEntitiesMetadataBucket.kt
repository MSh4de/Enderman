package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata

class ZombieMetadataBucket : LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(12,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.CHILD,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.CHILD
        )
        registerEntityMetadata(13,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.VILLAGER,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.VILLAGER
        )
        registerEntityMetadata(14,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.CONVERTING,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.CONVERTING
        )
    }
}

class SpiderMetadataBucket : LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.CLIMBING,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.CLIMBING
        )
    }
}