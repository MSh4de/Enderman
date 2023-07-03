package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.item.metadata.CanDestroyItemStackMetadata
import eu.mshade.enderframe.mojang.NamespacedKey
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.StringBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.util.function.Consumer

class CanDestroyItemStackMetadataWrapper(private val namespacedKeyWrapper: Wrapper<MaterialKey, NamespacedKey>?) :
    ItemStackMetadataWrapper {
    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val canDestroyItemStackMetadata =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_DESTROY) as CanDestroyItemStackMetadata

        val materialKeys = canDestroyItemStackMetadata.metadataValue

        val listBinaryTag = ListBinaryTag(BinaryTagType.STRING)

        materialKeys.forEach(Consumer { materialKey: MaterialKey ->
            val namespacedKey = namespacedKeyWrapper?.map(materialKey)
            if (namespacedKey != null) {
                listBinaryTag.add(StringBinaryTag(namespacedKey.toString()))
            }
        })

        if (listBinaryTag.isEmpty()) {
            return
        }
        compoundBinaryTag.putBinaryTag("CanDestroy", listBinaryTag)
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("CanDestroy")) return
        val canDestroy = compoundBinaryTag.getBinaryTag("CanDestroy") as? ListBinaryTag?: return
        if (canDestroy.isEmpty()) return
        val canDestroyItemStackMetadata = CanDestroyItemStackMetadata(mutableSetOf())
        for (binaryTag in canDestroy.value) {
            val namespacedKey = NamespacedKey.fromString(binaryTag.value as String?)
            val materialKey = namespacedKeyWrapper?.reverseMap(namespacedKey)?: continue
            canDestroyItemStackMetadata.metadataValue.add(materialKey)
        }
        metadataKeyValueBucket.setMetadataKeyValue(canDestroyItemStackMetadata)
    }
}
