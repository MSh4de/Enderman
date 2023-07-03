package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.attribute.AttributeKey
import eu.mshade.enderframe.attribute.AttributeModifier
import eu.mshade.enderframe.attribute.AttributeOperation
import eu.mshade.enderframe.inventory.EquipmentSlot
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackAttributeModifier
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.AttributeModifiersItemStackMetadata
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag
import java.util.UUID

class AttributeModifiersItemStackMetadataWrapper(
    private val endermanEquipmentSlotWrapper: Wrapper<EquipmentSlot, String>?,
    private val endermanAttributeKeyWrapper: Wrapper<AttributeKey, String>?
) : ItemStackMetadataWrapper {

    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        val metadataKeyValue =
            metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.ATTRIBUTE_MODIFIERS) as AttributeModifiersItemStackMetadata
        val metadataValue = metadataKeyValue.metadataValue
        if (metadataValue.isEmpty()) return
        val attributeModifiersListBinaryTag = ListBinaryTag(BinaryTagType.COMPOUND)
        for (itemStackAttributeModifier in metadataValue) {
            val attributeName = endermanAttributeKeyWrapper?.map(itemStackAttributeModifier.attributeKey)
            val equipmentSlot = endermanEquipmentSlotWrapper?.map(itemStackAttributeModifier.equipmentSlot)
            if (attributeName != null && equipmentSlot != null) {
                val attributeModifierCompoundBinaryTag = CompoundBinaryTag()
                attributeModifierCompoundBinaryTag.putString("AttributeName", attributeName)
                //                attributeModifierCompoundBinaryTag.putString("Name", itemStackAttributeModifier.getName());
                attributeModifierCompoundBinaryTag.putString("Name", attributeName)
                attributeModifierCompoundBinaryTag.putString("Slot", equipmentSlot)
                val attributeModifier = itemStackAttributeModifier.attributeModifier
                attributeModifierCompoundBinaryTag.putInt("Operation", attributeModifier.attributeOperation.ordinal)
                attributeModifierCompoundBinaryTag.putDouble("Amount", attributeModifier.amount)
                attributeModifierCompoundBinaryTag.putLong("UUIDMost", attributeModifier.uuid.mostSignificantBits)
                attributeModifierCompoundBinaryTag.putLong("UUIDLeast", attributeModifier.uuid.leastSignificantBits)
                attributeModifiersListBinaryTag.add(attributeModifierCompoundBinaryTag)
            }
        }

        if (attributeModifiersListBinaryTag.isEmpty()) return

        compoundBinaryTag.putBinaryTag("AttributeModifiers", attributeModifiersListBinaryTag)
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadataKeyValueBucket = itemStack.metadatas
        if (!compoundBinaryTag.containsKey("AttributeModifiers")) return
        val attributeModifiersListBinaryTag =
            compoundBinaryTag.getBinaryTag("AttributeModifiers") as ListBinaryTag
        val attributeModifiers = mutableListOf<ItemStackAttributeModifier>()
        for (entry in attributeModifiersListBinaryTag.value) {
            if (entry !is CompoundBinaryTag) continue
            val attributeName = entry.getString("AttributeName")?: continue
            val name = entry.getString("Name")?: continue
            val equipmentSlot = entry.getString("Slot")?: continue
            val attributeOperation = AttributeOperation.values()[entry.getInt("Operation")]
            val amount = entry.getDouble("Amount")
            val uuidMost = entry.getLong("UUIDMost")
            val uuidLeast = entry.getLong("UUIDLeast")
            val attributeKey = endermanAttributeKeyWrapper?.reverseMap(attributeName)
            val equipmentSlotKey = endermanEquipmentSlotWrapper?.reverseMap(equipmentSlot)
            if (attributeKey != null && equipmentSlotKey != null) {
                val itemStackAttributeModifier = ItemStackAttributeModifier(
                    attributeKey,
                    name,
                    equipmentSlotKey,
                    AttributeModifier(UUID(uuidMost, uuidLeast), amount, attributeOperation)
                )
                attributeModifiers.add(itemStackAttributeModifier)
            }


        }
        val attributeModifiersItemStackMetadata = AttributeModifiersItemStackMetadata(attributeModifiers)
        metadataKeyValueBucket.setMetadataKeyValue(attributeModifiersItemStackMetadata)
    }
}
