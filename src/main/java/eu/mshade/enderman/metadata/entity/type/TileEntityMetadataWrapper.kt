package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.ArmorStand
import eu.mshade.enderframe.entity.metadata.*
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.ByteMetadata

class ArmorStandPropertiesMetadataWrapper : MetadataWrapper<ArmorStand> {

    override fun wrap(armorStand: ArmorStand): Metadata<*> {
        val metadataKeyValueBucket = armorStand.metadataKeyValueBucket
        var b = 0

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.SMALL_ARMOR_STAND,
                SmallArmorStandEntityMetadata(false))
                .metadataValue
        ) b = b or 0x01

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.GRAVITY,
                GravityEntityMetadata(false))
                .metadataValue
        ) b = b or 0x02

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.ARMS,
                ArmsEntityMetadata(false))
                .metadataValue
        ) b = b or 0x04

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.BASE_PLATE,
                BasePlateEntityMetadata(false))
                .metadataValue
        ) b = b or 0x08

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(
                EntityMetadataKey.MARKER,
                SmallArmorStandEntityMetadata(false))
                .metadataValue
        ) b = b or 0x10

        return ByteMetadata(b)
    }
}