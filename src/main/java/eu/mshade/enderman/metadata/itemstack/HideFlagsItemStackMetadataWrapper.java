package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemFlag;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataWrapper;
import eu.mshade.enderframe.item.metadata.HideFlagsItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class HideFlagsItemStackMetadataWrapper implements ItemStackMetadataWrapper {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        HideFlagsItemStackMetadata hideFlagsItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.HIDE_FLAGS, HideFlagsItemStackMetadata.class);
        if (hideFlagsItemStackMetadata.getMetadataValue().isEmpty()) return;
        compoundBinaryTag.putInt("HideFlags", ItemFlag.toByte(hideFlagsItemStackMetadata.getMetadataValue()));

    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("HideFlags")) return;
        int v = compoundBinaryTag.getInt("HideFlags");
        HideFlagsItemStackMetadata hideFlagsItemStackMetadata = new HideFlagsItemStackMetadata(ItemFlag.fromByte(v));
        metadataKeyValueBucket.setMetadataKeyValue(hideFlagsItemStackMetadata);
    }
}
