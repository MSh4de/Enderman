package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.FireworkExplosion
import eu.mshade.enderframe.item.FireworkType
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataBuffer
import eu.mshade.enderframe.item.metadata.FireworkExplosionItemStackMetadata
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey
import eu.mshade.enderframe.mojang.Color
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class FireworkExplosionItemStackMetadataBuffer : ItemStackMetadataBuffer {
    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadataKeyValueBucket
        val metadataKeyValue = metadataKeyValueBucket.getMetadataKeyValue(
            ItemStackMetadataKey.EXPLOSION,
            FireworkExplosionItemStackMetadata::class.java
        )
        val explosion: FireworkExplosion = metadataKeyValue.metadataValue
        val explosionTag = compoundBinaryTag.computeIfAbsent("Explosion") { CompoundBinaryTag() }
        explosionTag.putByte("Flicker", explosion.flicker)
        explosionTag.putByte("Trail", explosion.trail)
        explosionTag.putByte("Type", explosion.type.id.toByte())
        explosionTag.putIntArray(
            "Colors",
            intArrayOf(explosion.color.red, explosion.color.green, explosion.color.blue)
        )
        if (explosion.fadeColor != null) {
            explosionTag.putIntArray(
                "FadeColors",
                intArrayOf(explosion.fadeColor!!.red, explosion.fadeColor!!.green, explosion.fadeColor!!.blue)
            )
        }
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadataKeyValueBucket
        if(!compoundBinaryTag.containsKey("Explosion")) return
        val explosionTag = compoundBinaryTag.getBinaryTag("Explosion") as CompoundBinaryTag

        if (!explosionTag.containsKey("Flicker")) return
        val flicker = explosionTag.getByte("Flicker") == 1.toByte()

        if (!explosionTag.containsKey("Trail")) return
        val trail = explosionTag.getByte("Trail") == 1.toByte()

        if (!explosionTag.containsKey("Type")) return
        val type = FireworkType.getFireworkType(explosionTag.getByte("Type").toInt())

        if (!explosionTag.containsKey("Colors")) return
        val colorsArray = explosionTag.getIntArray("Colors")
        val color = Color.fromRGB(colorsArray[0], colorsArray[1], colorsArray[2])

        var fadeColor: Color? = null
        if (explosionTag.containsKey("FadeColors")) {
            val fadeColorsArray = explosionTag.getIntArray("FadeColors")
            fadeColor = Color.fromRGB(fadeColorsArray[0], fadeColorsArray[1], fadeColorsArray[2])
        }

        FireworkExplosionItemStackMetadata(FireworkExplosion(flicker, trail, type, color, fadeColor)).apply {
            metadataKeyValueBucket.setMetadataKeyValue(this)
        }
    }
}