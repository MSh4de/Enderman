package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.Horse
import eu.mshade.enderframe.entity.Sheep
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.ByteMetadata
import eu.mshade.enderframe.metadata.type.IntegerMetadata

class HorseBehavioursPropertiesMetadataWrapper: MetadataWrapper<Horse> {

    override fun wrap(horse: Horse): Metadata<*> {
        val metadataKeyValueBucket = horse.metadataKeyValueBucket
        var b = 0

        val tamed = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.TAMED)?.metadataValue)?: false) as Boolean
        val saddle = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.SADDLE)?.metadataValue)?: false) as Boolean
        val hasChest = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.HAS_CHEST)?.metadataValue)?: false) as Boolean
        val bred = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.IS_BRED)?.metadataValue)?: false) as Boolean
        val eating = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.EATING)?.metadataValue)?: false) as Boolean
        val rearing = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.IS_REARING)?.metadataValue)?: false) as Boolean
        val mouthOpen = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.MOUTH_OPEN)?.metadataValue)?: false) as Boolean

        if (tamed)
            b = b or 0x02

        if (saddle)
            b = b or 0x04

        if (hasChest)
            b = b or 0x08

        if (bred)
            b = b or 0x10

        if (eating)
            b = b or 0x20

        if (rearing)
            b = b or 0x40

        if (mouthOpen)
            b = b or 0x80

        return IntegerMetadata(b)
    }
}

class HorseStylePropertiesMetadataWrapper: MetadataWrapper<Horse> {

    override fun wrap(horse: Horse): Metadata<*> {
        val metadataKeyValueBucket = horse.metadataKeyValueBucket

        val color = (metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.HORSE_COLOR)?.metadataValue)?: 0
        val style = (metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.STYLE)?.metadataValue)?: 0

        var b = 0
        b = b or (color as Int)
        b = b or ((style as Int) shl 8)

        return IntegerMetadata(b)
    }
}

class SheepPropertiesMetadataWrapper: MetadataWrapper<Sheep> {

    override fun wrap(sheep: Sheep): Metadata<*> {
        val metadataBucket = sheep.metadataKeyValueBucket

        val color = (metadataBucket.getMetadataKeyValue(EntityMetadataKey.COLOR)?.metadataValue?: 0) as Int
        val sheared = (metadataBucket.getMetadataKeyValue(EntityMetadataKey.IS_SHEARED)?.metadataValue?: false) as Boolean

        var b = color
        if (sheared)
            b = b or 0x10

        return ByteMetadata(b)
    }

}