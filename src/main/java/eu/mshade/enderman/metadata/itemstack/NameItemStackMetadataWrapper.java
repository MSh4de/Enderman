package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataWrapper;
import eu.mshade.enderframe.item.metadata.NameItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class NameItemStackMetadataWrapper implements ItemStackMetadataWrapper {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadatas();
        NameItemStackMetadata nameItemStackMetadata = (NameItemStackMetadata) metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.NAME);
        CompoundBinaryTag displayCompoundBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
        displayCompoundBinaryTag.putString("Name", nameItemStackMetadata.getMetadataValue().toLegacyText());
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadatas();
        if (!compoundBinaryTag.containsKey("display")) return;
        CompoundBinaryTag displayCompoundBinaryTag = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        if (!displayCompoundBinaryTag.containsKey("Name")) return;
        metadataKeyValueBucket.setMetadataKeyValue(new NameItemStackMetadata(displayCompoundBinaryTag.getString("Name")));
    }
}
