package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.ColorItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.Color;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class ColorItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        ColorItemStackMetadata metadataKeyValue = (ColorItemStackMetadata) metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.COLOR);
        Color color = metadataKeyValue.getMetadataValue();
        CompoundBinaryTag displayCompoundBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
        displayCompoundBinaryTag.putInt("color", color.asRGB());
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("display")) return;
        CompoundBinaryTag display = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        if (!display.containsKey("color")) return;
        Color color = Color.fromRGB(display.getInt("color"));
        ColorItemStackMetadata colorItemStackMetadata = new ColorItemStackMetadata(color);
        metadataKeyValueBucket.setMetadataKeyValue(colorItemStackMetadata);
    }
}
