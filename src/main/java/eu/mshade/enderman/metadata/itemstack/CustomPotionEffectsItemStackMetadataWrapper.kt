package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.effect.PotionEffect
import eu.mshade.enderframe.effect.PotionEffectKey
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.CustomPotionEffectsItemStackMetadata
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

class CustomPotionEffectsItemStackMetadataWrapper(val effectWrapper: Wrapper<PotionEffectKey, Int>?) : ItemStackMetadataWrapper {

    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val metadataKeyValue =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CUSTOM_POTION_EFFECTS) as CustomPotionEffectsItemStackMetadata
        val effects = metadataKeyValue.metadataValue
        val effectsTag = compoundBinaryTag.computeIfAbsent("CustomPotionEffects") { ListBinaryTag(BinaryTagType.COMPOUND) }
        for (effect in effects) {
            val effectId = effectWrapper?.map(effect.type)?: continue
            val effectTag = CompoundBinaryTag()
            effectTag.putByte("Id", effectId.toByte())
            effectTag.putByte("Amplifier", effect.amplifier.toByte())
            effectTag.putInt("Duration", effect.duration)
            effectTag.putByte("Ambient", effect.ambient)
            effectTag.putByte("ShowParticles", effect.particle)
            effectsTag.add(effectTag)
        }

        if (effectsTag.isEmpty()) return

        compoundBinaryTag.putBinaryTag("CustomPotionEffects", effectsTag)
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadatas = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("CustomPotionEffects")) return
        val effectsTag = compoundBinaryTag.getBinaryTag("CustomPotionEffects") as ListBinaryTag
        val potionEffects = mutableListOf<PotionEffect>()
        for (effectTag in effectsTag.value) {
            if (effectTag !is CompoundBinaryTag) continue
            if (!effectTag.containsKey("Id")) continue
            val effectId = effectTag.getByte("Id").toInt()
            val effectType = effectWrapper?.reverseMap(effectId)?: continue
            val amplifier = effectTag.getByte("Amplifier").toInt()
            val duration = effectTag.getInt("Duration")
            val ambient = effectTag.getByte("Ambient") == 1.toByte()
            val particle = effectTag.getByte("ShowParticles") == 1.toByte()
            potionEffects.add(PotionEffect(effectType, amplifier, duration, ambient, particle))
        }

        metadatas.setMetadataKeyValue(CustomPotionEffectsItemStackMetadata(potionEffects))
    }

}