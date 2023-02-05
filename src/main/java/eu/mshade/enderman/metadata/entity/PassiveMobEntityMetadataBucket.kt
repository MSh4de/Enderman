package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.IntegerMetadata
import eu.mshade.enderframe.metadata.type.StringMetadata
import eu.mshade.enderman.metadata.entity.type.HorseBehavioursPropertiesMetadataWrapper
import eu.mshade.enderman.metadata.entity.type.HorseStylePropertiesMetadataWrapper
import eu.mshade.enderman.metadata.entity.type.SheepPropertiesMetadataWrapper

class BatMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_HANGING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_HANGING
        )
    }
}

class ChickenMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_HANGING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_HANGING
        )
    }
}

class HorseMetadataBucket: LivingEntityMetadataBucket() {
    init {
        registerEntityMetadata(16,
            HorseBehavioursPropertiesMetadataWrapper(),
            EntityMetadataKey.TAMED, EntityMetadataKey.SADDLE, EntityMetadataKey.HAS_CHEST, EntityMetadataKey.IS_BRED, EntityMetadataKey.EATING, EntityMetadataKey.IS_REARING, EntityMetadataKey.MOUTH_OPEN
        )

        registerEntityMetadata(19,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.VARIANT
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.VARIANT
        )

        registerEntityMetadata(20,
            HorseStylePropertiesMetadataWrapper(),
            EntityMetadataKey.HORSE_COLOR, EntityMetadataKey.STYLE
        )

        registerEntityMetadata(21,
            { entity: Entity ->
                StringMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.OWNER
                    ).metadataValue as String
                )
            }, EntityMetadataKey.OWNER
        )

        registerEntityMetadata(22,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.ARMOR
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.ARMOR
        )
    }
}

class OcelotMetadataBucket: TameableEntityMetadataBucket() {
    init {
        registerEntityMetadata(18,
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

class PigMetadataBucket: AgeableEntityMetadataBucket() {
    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.SADDLE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.SADDLE
        )
    }
}

class RabbitMetadataBucket: AgeableEntityMetadataBucket() {

    init {
        registerEntityMetadata(18,
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

class SheepMetadataBucket: AgeableEntityMetadataBucket() {

    init {
        registerEntityMetadata(
            16,
            SheepPropertiesMetadataWrapper(),
            EntityMetadataKey.COLOR, EntityMetadataKey.IS_SHEARED
        )
    }
}

class VillagerMetadataBucket: AgeableEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.VARIANT
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.VARIANT
        )
    }
}