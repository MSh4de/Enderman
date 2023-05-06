package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.entity.EntityMetadataBucket
import eu.mshade.enderframe.metadata.type.*
import eu.mshade.enderman.metadata.entity.type.PropertiesMetadataWrapper
import eu.mshade.enderman.metadata.entity.type.TameablePropertiesMetadataWrapper


open class DefaultEntityMetadataBucket : EntityMetadataBucket() {

    init {
        registerEntityMetadata(0, PropertiesMetadataWrapper(), EntityMetadataKey.ON_FIRE, EntityMetadataKey.CROUCHED, EntityMetadataKey.SPRINTING, EntityMetadataKey.HANDLING, EntityMetadataKey.INVISIBLE)

        registerEntityMetadata(1,
            { entity: Entity ->
                ShortMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.AIR_TICKS
                    ).metadataValue as Short
                )
            }, EntityMetadataKey.AIR_TICKS
        )
        //????
//        registerEntityMetadata(4,
//            { entity: Entity ->
//                ByteMetadata(
//                    entity.metadataKeyValueBucket.getMetadataKeyValue(
//                        EntityMetadataKey.SILENT
//                    ).metadataValue as Boolean
//                )
//            }, EntityMetadataKey.SILENT
//        )
    }
}

open class LivingEntityMetadataBucket : DefaultEntityMetadataBucket() {

    init {
        registerEntityMetadata(2,
            { entity: Entity ->
                StringMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.CUSTOM_NAME
                    ).metadataValue as String
                )
            }, EntityMetadataKey.CUSTOM_NAME
        )
        registerEntityMetadata(3,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.CUSTOM_NAME_VISIBLE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.CUSTOM_NAME_VISIBLE
        )
        registerEntityMetadata(6,
            { entity: Entity ->
                FloatMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.HEALTH
                    ).metadataValue as Float
                )
            }, EntityMetadataKey.HEALTH
        )
        registerEntityMetadata(7,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.POTION_EFFECT_COLOR
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.POTION_EFFECT_COLOR
        )
        registerEntityMetadata(8,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.POTION_EFFECT_AMBIENT
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.POTION_EFFECT_AMBIENT
        )
        registerEntityMetadata(9,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY
        )
        registerEntityMetadata(15,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE
                    ).metadataValue as Boolean
                )
            }, EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE
        )
    }
}

open class AgeableEntityMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(12,
            { entity: Entity ->
                ByteMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.AGE
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.AGE
        )
    }
}

open class TameableEntityMetadataBucket: AgeableEntityMetadataBucket() {

    init {
        registerEntityMetadata(16,
            TameablePropertiesMetadataWrapper(),
            EntityMetadataKey.SITTING, EntityMetadataKey.TAMED
        )

        registerEntityMetadata(17,
            { entity: Entity ->
                StringMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.OWNER
                    ).metadataValue as String
                )
            }, EntityMetadataKey.OWNER
        )
    }
}