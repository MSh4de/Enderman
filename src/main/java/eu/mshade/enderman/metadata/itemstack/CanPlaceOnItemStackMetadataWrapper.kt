package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.item.metadata.CanPlaceOnItemStackMetadata
import eu.mshade.enderframe.mojang.NamespacedKey
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.StringBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.util.function.Consumer

class CanPlaceOnItemStackMetadataWrapper(private val endermanNamespacedKeyWrapper: Wrapper<MaterialKey, NamespacedKey>?) :
    ItemStackMetadataWrapper {

    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val canPlaceOnItemStackMetadata =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_PLACE_ON) as CanPlaceOnItemStackMetadata
        val materialKeys = canPlaceOnItemStackMetadata.metadataValue
        val listBinaryTag = ListBinaryTag(BinaryTagType.STRING)
        materialKeys.forEach(Consumer { materialKey: MaterialKey ->
            val namespacedKey = endermanNamespacedKeyWrapper?.map(materialKey)
            if (namespacedKey != null) {
                listBinaryTag.add(StringBinaryTag(namespacedKey.toString()))
            }
        })
        compoundBinaryTag.putBinaryTag("CanPlaceOn", listBinaryTag)
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("CanPlaceOn")) return
        val canPlaceOn = compoundBinaryTag.getBinaryTag("CanPlaceOn") as ListBinaryTag?
        if (canPlaceOn!!.isEmpty()) return
        val canPlaceOnItemStackMetadata = CanPlaceOnItemStackMetadata(mutableSetOf())
        for (binaryTag in canPlaceOn.value) {
            val namespacedKey = NamespacedKey.fromString(binaryTag.value as String?)
            val materialKey = endermanNamespacedKeyWrapper?.reverseMap(namespacedKey)?: continue
            canPlaceOnItemStackMetadata.metadataValue.add(materialKey)
        }
        metadataKeyValueBucket.setMetadataKeyValue(canPlaceOnItemStackMetadata)
    }
}
