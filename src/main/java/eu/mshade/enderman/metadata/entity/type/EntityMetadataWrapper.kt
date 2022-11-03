package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.metadata.*
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.ByteMetadata

class PropertiesMetadataWrapper: MetadataWrapper<Entity> {

    override fun wrap(entity: Entity): Metadata<*> {
        val metadataKeyValueBucket: MetadataKeyValueBucket = entity.metadataKeyValueBucket
        var b = 0

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.ON_FIRE,
                OnFireEntityMetadata(false))
                .metadataValue
        ) b = b or 0x01

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.CROUCHED,
                CrouchedEntityMetadata(false))
                .metadataValue
        ) b = b or 0x02

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.SPRINTING,
                SprintingEntityMetadata(false))
                .metadataValue
        ) b = b or 0x08

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.HANDLING,
                HandlingEntityMetadata(false))
                .metadataValue
        ) b = b or 0x10

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.INVISIBLE,
                InvisibleEntityMetadata(false))
                .metadataValue
        ) b = b or 0x20
        return ByteMetadata(b)
    }

}