package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackAttributeModifier;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.AttributeModifiersItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.attribute.AttributeModifier;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderman.wrapper.EndermanAttributeKeyWrapper;
import eu.mshade.enderman.wrapper.EndermanEquipmentSlotWrapper;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.util.List;

public class AttributeModifiersItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    private EndermanEquipmentSlotWrapper endermanEquipmentSlotWrapper;
    private EndermanAttributeKeyWrapper endermanAttributeKeyWrapper;

    public AttributeModifiersItemStackMetadataBuffer(EndermanEquipmentSlotWrapper endermanEquipmentSlotWrapper, EndermanAttributeKeyWrapper endermanAttributeKeyWrapper) {
        this.endermanEquipmentSlotWrapper = endermanEquipmentSlotWrapper;
        this.endermanAttributeKeyWrapper = endermanAttributeKeyWrapper;
    }

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        AttributeModifiersItemStackMetadata metadataKeyValue = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.ATTRIBUTE_MODIFIERS, AttributeModifiersItemStackMetadata.class);
        List<ItemStackAttributeModifier> metadataValue = metadataKeyValue.getMetadataValue();
        if (metadataValue.isEmpty()) return;
        ListBinaryTag attributeModifiersListBinaryTag = new ListBinaryTag(BinaryTagType.COMPOUND);
        for (ItemStackAttributeModifier itemStackAttributeModifier : metadataValue) {
            if (endermanAttributeKeyWrapper.isSupport(itemStackAttributeModifier.getAttributeKey())) {
                CompoundBinaryTag attributeModifierCompoundBinaryTag = new CompoundBinaryTag();
                attributeModifierCompoundBinaryTag.putString("AttributeName", endermanAttributeKeyWrapper.wrap(itemStackAttributeModifier.getAttributeKey()));
                attributeModifierCompoundBinaryTag.putString("Name", itemStackAttributeModifier.getName());
                attributeModifierCompoundBinaryTag.putString("Slot", endermanEquipmentSlotWrapper.wrap(itemStackAttributeModifier.getEquipmentSlot()));
                AttributeModifier attributeModifier = itemStackAttributeModifier.getAttributeModifier();
                attributeModifierCompoundBinaryTag.putByte("Operation", attributeModifier.getAttributeOperation().ordinal());
                attributeModifierCompoundBinaryTag.putDouble("Amount", attributeModifier.getAmount());
                attributeModifierCompoundBinaryTag.putLong("UUIDMost", attributeModifier.getUuid().getMostSignificantBits());
                attributeModifierCompoundBinaryTag.putLong("UUIDLeast", attributeModifier.getUuid().getLeastSignificantBits());
                attributeModifiersListBinaryTag.add(attributeModifierCompoundBinaryTag);
            }
        }

        compoundBinaryTag.putBinaryTag("AttributeModifiers", attributeModifiersListBinaryTag);

    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {

    }
}
