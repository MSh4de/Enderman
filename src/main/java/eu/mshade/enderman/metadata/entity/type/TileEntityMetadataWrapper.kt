package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.ArmorStand
import eu.mshade.enderframe.entity.metadata.*
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.ByteMetadata

class ArmorStandPropertiesMetadataWrapper : MetadataWrapper<ArmorStand> {

    override fun wrap(armorStand: ArmorStand): Metadata<Byte> {
        val metadataKeyValueBucket = armorStand.metadata
        var b = 0

        val small = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.SMALL_ARMOR_STAND)?.metadataValue)?: false) as Boolean
        val gravity = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.GRAVITY)?.metadataValue)?: false) as Boolean
        val arms = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.ARMS)?.metadataValue)?: false) as Boolean
        val removeBasePlate = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.REMOVE_BASE_PLATE)?.metadataValue)?: false) as Boolean
        val marker = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.MARKER)?.metadataValue)?: false) as Boolean

        if (small)
            b = b or 0x01

        if (gravity)
            b = b or 0x02

        if (arms)
            b = b or 0x04

        if (removeBasePlate)
            b = b or 0x08

        if (marker)
            b = b or 0x10

        return ByteMetadata(b)
    }
}