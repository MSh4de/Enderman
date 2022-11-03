package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.IntegerMetadata
import eu.mshade.enderframe.metadata.type.RotationMetadata
import eu.mshade.enderframe.world.Rotation
import eu.mshade.enderman.metadata.entity.type.ArmorStandPropertiesMetadataWrapper

class ArmorStandMetadataBucket : LivingEntityMetadataBucket() {
    init {
        registerEntityMetadata(
            10,
            ArmorStandPropertiesMetadataWrapper(),
            EntityMetadataKey.SMALL_ARMOR_STAND,
            EntityMetadataKey.GRAVITY,
            EntityMetadataKey.ARMS,
            EntityMetadataKey.BASE_PLATE,
            EntityMetadataKey.MARKER
        )
        registerEntityMetadata(11,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.HEAD_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.HEAD_ROTATION
        )
        registerEntityMetadata(12,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.BODY_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.BODY_ROTATION
        )
        registerEntityMetadata(13,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.LEFT_ARM_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.LEFT_ARM_ROTATION
        )
        registerEntityMetadata(14,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.RIGHT_ARM_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.RIGHT_ARM_ROTATION
        )
        registerEntityMetadata(15,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.LEFT_LEG_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.LEFT_LEG_ROTATION
        )
        registerEntityMetadata(16,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.RIGHT_LEG_ROTATION,
                        Rotation::class.java
                    )
                )
            }, EntityMetadataKey.RIGHT_LEG_ROTATION
        )
    }
}

class BoatMetadataBucket : DefaultEntityMetadataBucket() {

    init {
        registerEntityMetadata(17,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.TIME_SINCE_HIT,
                        Int::class.java
                    )
                )
            }, EntityMetadataKey.TIME_SINCE_HIT)

        registerEntityMetadata(18,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.FORWARD_DIRECTION,
                        Int::class.java
                    )
                )
            }, EntityMetadataKey.FORWARD_DIRECTION)

        registerEntityMetadata(19,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.DAMAGE_TAKEN,
                        Float::class.java
                    ).toInt()
                )
            }, EntityMetadataKey.DAMAGE_TAKEN
        )
    }
}

class EnderCrystalMetadataBucket : DefaultEntityMetadataBucket() {
    init {
        registerEntityMetadata(8,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.HEALTH,
                        Float::class.java
                    ).toInt()
                )
            }, EntityMetadataKey.HEALTH
        )
    }
}