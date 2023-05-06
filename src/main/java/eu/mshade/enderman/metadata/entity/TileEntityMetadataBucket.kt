package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.FloatMetadata
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
            EntityMetadataKey.REMOVE_BASE_PLATE,
            EntityMetadataKey.MARKER
        )
        registerEntityMetadata(11,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.HEAD_ROTATION
                    ).metadataValue as Rotation
                )
            }, EntityMetadataKey.HEAD_ROTATION
        )
        registerEntityMetadata(12,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.BODY_ROTATION
                    ).metadataValue as Rotation
                )
            }, EntityMetadataKey.BODY_ROTATION
        )
        registerEntityMetadata(13,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.LEFT_ARM_ROTATION
                    ).metadataValue as Rotation
                )
            }, EntityMetadataKey.LEFT_ARM_ROTATION
        )
        registerEntityMetadata(14,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.RIGHT_ARM_ROTATION
                    ).metadataValue as Rotation
                )
            }, EntityMetadataKey.RIGHT_ARM_ROTATION
        )
        registerEntityMetadata(15,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.LEFT_LEG_ROTATION
                    ).metadataValue as Rotation
                )
            }, EntityMetadataKey.LEFT_LEG_ROTATION
        )
        registerEntityMetadata(16,
            { entity: Entity ->
                RotationMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.RIGHT_LEG_ROTATION
                    ).metadataValue as Rotation
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
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.TIME_SINCE_LAST_HIT
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.TIME_SINCE_LAST_HIT)

        registerEntityMetadata(18,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.FORWARD_DIRECTION
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.FORWARD_DIRECTION)

        registerEntityMetadata(19,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.DAMAGE_TAKEN
                    ).metadataValue as Float
                )
            }, EntityMetadataKey.DAMAGE_TAKEN
        )
    }
}

open class MinecartMetadataBucket : DefaultEntityMetadataBucket() {

    init {
    }
}

class FurnaceMinecartMetadataBucket : MinecartMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.IS_POWERED
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.IS_POWERED)
    }
}

class ItemMetadataBucket : DefaultEntityMetadataBucket() {

    init {
    }
}

class ArrowMetadataBucket : DefaultEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.CRITICAL
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.CRITICAL
        )
    }
}

class FireworkMetadataBucket : DefaultEntityMetadataBucket() {

    init {
    }
}

class ItemFrameMetadataBucket : DefaultEntityMetadataBucket() {

    init {
    }
}

class EnderCrystalMetadataBucket : DefaultEntityMetadataBucket() {
    init {
        registerEntityMetadata(8,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.HEALTH
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.HEALTH
        )
    }
}