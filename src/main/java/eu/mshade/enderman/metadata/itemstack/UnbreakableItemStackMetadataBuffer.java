package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.UnbreakableItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.mwork.binarytag.ByteBinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class UnbreakableItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        Boolean v = metadataKeyValueBucket.getValueOfMetadataKeyValue(ItemStackMetadataKey.UNBREAKABLE, Boolean.class);
        compoundBinaryTag.putBinaryTag("Unbreakable", new ByteBinaryTag(v));
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("Unbreakable")) return;
        boolean unbreakable = compoundBinaryTag.getByte("Unbreakable") == 1;
        metadataKeyValueBucket.setMetadataKeyValue(new UnbreakableItemStackMetadata(unbreakable));
    }

}
