package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.IntegerMetadata
import eu.mshade.enderman.metadata.entity.type.GuardianPropertiesMetadataWrapper

class BlazeMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.ON_FIRE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.ON_FIRE
        )
    }
}

class CreeperMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.CREEPER_STATE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.CREEPER_STATE
        )

        registerEntityMetadata(17,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_POWERED
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_POWERED
        )
    }
}

class GhastMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_ATTACKING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_ATTACKING
        )
    }
}

class GuardianMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            GuardianPropertiesMetadataWrapper(),
            EntityMetadataKey.IS_ELDERLY, EntityMetadataKey.IS_RETRACTING_SPIKES
        )
        registerEntityMetadata(17,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.TARGET_ENTITY_ID
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.TARGET_ENTITY_ID
        )
    }
}

class MagmaCubeMetadataBucket: SlimeMetadataBucket() {
}

class SkeletonMetadataBucket: LivingEntityMetadataBucket() {
    init {
        registerEntityMetadata(13,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.VARIANT
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.VARIANT
        )
    }
}

open class SlimeMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.SIZE
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.SIZE
        )
    }
}

class WitchMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(21,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_AGGRESSIVE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_AGGRESSIVE
        )
    }
}

open class ZombieMetadataBucket : LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(12,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.CHILD
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.CHILD
        )
        registerEntityMetadata(13,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_VILLAGER
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_VILLAGER
        )
        registerEntityMetadata(14,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_CONVERTING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_CONVERTING
        )
    }
}