package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemFlag
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.HideFlagsItemStackMetadata
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class HideFlagsItemStackMetadataWrapper : ItemStackMetadataWrapper {
    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val hideFlagsItemStackMetadata =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.HIDE_FLAGS) as HideFlagsItemStackMetadata
        if (hideFlagsItemStackMetadata.metadataValue.isEmpty()) return
        compoundBinaryTag.putInt("HideFlags", ItemFlag.toByte(hideFlagsItemStackMetadata.metadataValue))
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("HideFlags")) return
        val v = compoundBinaryTag.getInt("HideFlags")
        val hideFlagsItemStackMetadata = HideFlagsItemStackMetadata(ItemFlag.fromByte(v))
        metadataKeyValueBucket.setMetadataKeyValue(hideFlagsItemStackMetadata)
    }
}
