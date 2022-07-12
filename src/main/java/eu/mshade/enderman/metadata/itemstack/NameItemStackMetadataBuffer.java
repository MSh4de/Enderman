package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.NameItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class NameItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        NameItemStackMetadata nameItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.NAME, NameItemStackMetadata.class);
        CompoundBinaryTag displayCompoundBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
        displayCompoundBinaryTag.putString("Name", nameItemStackMetadata.getMetadataValue().toLegacyText());
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("display")) return;
        CompoundBinaryTag displayCompoundBinaryTag = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        if (!displayCompoundBinaryTag.containsKey("Name")) return;
        metadataKeyValueBucket.setMetadataKeyValue(new NameItemStackMetadata(displayCompoundBinaryTag.getString("Name")));
    }
}
