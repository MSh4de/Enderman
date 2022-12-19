package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.FireworkExplosion
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataBuffer
import eu.mshade.enderframe.item.metadata.FireworkExplosionItemStackMetadata
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey
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

    override fun read(compoundBinaryTag: CompoundBinaryTag?, itemStack: ItemStack) {
        TODO("Not yet implemented")
    }
}