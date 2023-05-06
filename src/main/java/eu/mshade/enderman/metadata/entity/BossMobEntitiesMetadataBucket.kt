package eu.mshade.enderman.metadata.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.type.IntegerMetadata

class WitherMetadataBucket: LivingEntityMetadataBucket() {

    init {
        registerEntityMetadata(20,
            { entity: Entity ->
                IntegerMetadata(
                    entity.metadata.getMetadataKeyValue(
                        EntityMetadataKey.INVULNERABLE_TIME
                    ).metadataValue as Int
                )
            }, EntityMetadataKey.INVULNERABLE_TIME
        )
    }
}