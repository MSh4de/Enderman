package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.entity.EntityMetadataBucket
import eu.mshade.enderframe.metadata.type.*
import eu.mshade.enderman.metadata.entity.type.PropertiesMetadataWrapper


open class DefaultEntityMetadataBucket : EntityMetadataBucket() {

    init {
        registerEntityMetadata(0, PropertiesMetadataWrapper(), EntityMetadataKey.ON_FIRE, EntityMetadataKey.CROUCHED, EntityMetadataKey.SPRINTING, EntityMetadataKey.HANDLING, EntityMetadataKey.INVISIBLE)

        registerEntityMetadata(1,
            { entity: Entity ->
                ShortMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.AIR_TICKS,
                        Short::class.java
                    )
                )
            }, EntityMetadataKey.AIR_TICKS
        )
        registerEntityMetadata(2,
            { entity: Entity ->
                StringMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.CUSTOM_NAME,
                        String::class.java
                    )
                )
            }, EntityMetadataKey.CUSTOM_NAME
        )
        registerEntityMetadata(3,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.CUSTOM_NAME_VISIBLE,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.CUSTOM_NAME_VISIBLE
        )
        registerEntityMetadata(4,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.SILENT,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.SILENT
        )
    }
}

open class LivingEntityMetadataBucket : DefaultEntityMetadataBucket() {

    init {
        registerEntityMetadata(6,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.HEALTH,
                        Float::class.java
                    )
                )
            }, EntityMetadataKey.HEALTH
        )
        registerEntityMetadata(7,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.POTION_EFFECT_COLOR,
                        Int::class.java
                    )
                )
            }, EntityMetadataKey.POTION_EFFECT_COLOR
        )
        registerEntityMetadata(8,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.POTION_EFFECT_AMBIENT,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.POTION_EFFECT_AMBIENT
        )
        registerEntityMetadata(9,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY,
                        Int::class.java
                    )
                )
            }, EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY
        )
        registerEntityMetadata(15,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadataKeyValueBucket.getValueOfMetadataKeyValue(
                        EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE,
                        Boolean::class.java
                    )
                )
            }, EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE
        )
    }
}