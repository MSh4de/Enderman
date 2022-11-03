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
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.SKIN_PART,
                        SkinPart::class.java
                    ).toByte()
                )
            }, EntityMetadataKey.SKIN_PART
        )
        registerEntityMetadata(17,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.ADDITIONAL_HEARTS,
                        Float::class.java
                    )
                )
            }, EntityMetadataKey.ADDITIONAL_HEARTS
        )
        registerEntityMetadata(18,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.SCORE,
                        Int::class.java
                    )
                )
            }, EntityMetadataKey.SCORE
        )
    }
}