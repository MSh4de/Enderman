package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.Tameable
import eu.mshade.enderframe.entity.metadata.*
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.ByteMetadata

class PropertiesMetadataWrapper: MetadataWrapper<Entity> {

    override fun wrap(entity: Entity): Metadata<*> {
        val metadataKeyValueBucket: MetadataKeyValueBucket = entity.metadataKeyValueBucket
        var b = 0

        val onFire = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.ON_FIRE)?.metadataValue
        val crouched = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.CROUCHED)?.metadataValue
        val sprinting = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.SPRINTING)?.metadataValue
        val handling = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.HANDLING)?.metadataValue
        val invisible = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.INVISIBLE)?.metadataValue

        if ((onFire?: false) as Boolean)
            b = b or 0x01

        if ((crouched?: false) as Boolean)
            b = b or 0x02

        if ((sprinting?: false) as Boolean)
            b = b or 0x08

        if ((handling?: false) as Boolean)
            b = b or 0x10

        if ((invisible?: false) as Boolean)
            b = b or 0x20

        return ByteMetadata(b)
    }

}

class TameablePropertiesMetadataWrapper: MetadataWrapper<Entity> {
    override fun wrap(entity: Entity): Metadata<*> {
        val metadataKeyValueBucket = entity.metadataKeyValueBucket
        var b = 0

        val sitting = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.SITTING)?.metadataValue
        val tamed = metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.TAMED)?.metadataValue

        if ((sitting?: false) as Boolean)
            b = b or 0x01

        if ((tamed?: false) as Boolean)
            b = b or 0x04

        return ByteMetadata(b)
    }

}