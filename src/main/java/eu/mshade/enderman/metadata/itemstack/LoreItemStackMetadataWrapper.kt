package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.LoreItemStackMetadata
import eu.mshade.enderframe.mojang.chat.TextComponent
import eu.mshade.mwork.binarytag.BinaryTag
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.StringBinaryTag
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.util.function.Consumer

class LoreItemStackMetadataWrapper : ItemStackMetadataWrapper {
    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val loreItemStackMetadata =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.LORE) as LoreItemStackMetadata
        val displayCompoundBinaryTag =
            compoundBinaryTag.computeIfAbsent("display") { _ -> CompoundBinaryTag() }
        val lore = loreItemStackMetadata.metadataValue
        if (!lore.isEmpty()) {
            val listBinaryTag = ListBinaryTag(BinaryTagType.STRING)
            lore.forEach(Consumer { s: TextComponent -> listBinaryTag.add(StringBinaryTag(s.toLegacyText())) })
            displayCompoundBinaryTag.putBinaryTag("Lore", listBinaryTag)
        }
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("display")) return
        val display = compoundBinaryTag.getBinaryTag("display") as CompoundBinaryTag?
        if (!display!!.containsKey("Lore")) return
        val lore = display.getBinaryTag("Lore") as ListBinaryTag?
        val loreItemStackMetadata = LoreItemStackMetadata(ArrayList())
        lore!!.value.forEach(Consumer<BinaryTag<*>> { binaryTag: BinaryTag<*> ->
            loreItemStackMetadata.metadataValue.add(
                TextComponent.of(binaryTag.value as String?)
            )
        })
        metadataKeyValueBucket.setMetadataKeyValue(loreItemStackMetadata)
    }
}
