package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.FloatMetadata
import eu.mshade.enderframe.metadata.type.IntegerMetadata
import eu.mshade.enderframe.mojang.SkinPart

class PlayerMetadataBucket : LivingEntityMetadataBucket() {
    init {
        registerEntityMetadata(10,
            { entity: Entity ->
                ByteMetadata(
                    (entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.SKIN_PART
                    ).metadataValue as SkinPart).toByte()
                )
            }, EntityMetadataKey.SKIN_PART
        )
        registerEntityMetadata(16,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.HIDE_CAPE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.HIDE_CAPE
        )
        registerEntityMetadata(17,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.ADDITIONAL_HEARTS
                    ).metadataValue as Float
                )
            }, EntityMetadataKey.ADDITIONAL_HEARTS
        )
        registerEntityMetadata(18,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.SCORE
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.SCORE
        )
    }
}
