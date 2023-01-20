package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.FloatMetadata

class CaveSpiderMetadataBucket: SpiderMetadataBucket() {

}

class EndermanMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(18,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.SCREAMING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.SCREAMING
        )
    }
}

class IronGolemMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.IS_PLAYER_CREATED
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.IS_PLAYER_CREATED
        )
    }
}

open class SpiderMetadataBucket : LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.CLIMBING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.CLIMBING
        )
    }
}

class WolfMetadataBucket: TameableEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(if ((entity.metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.IS_ANGRY).metadataValue?: false) as Boolean) 0x02 else 0x00)
            }, EntityMetadataKey.IS_ANGRY
        )

        registerEntityMetadata(18,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.HEALTH
                    ).metadataValue as Float
                )
            }, EntityMetadataKey.HEALTH
        )

        registerEntityMetadata(19,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.BEGGING
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.BEGGING
        )

        registerEntityMetadata(20,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getMetadataKeyValue(
                        EntityMetadataKey.COLLAR_COLOR
                    ).metadataValue as Int)
            }, EntityMetadataKey.COLLAR_COLOR
        )
    }
}

class ZombifiedPiglinMetadataBucket: ZombieMetadataBucket() {

}