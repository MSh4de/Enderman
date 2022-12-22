package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.LoreItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

import java.util.ArrayList;
import java.util.List;

public class LoreItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        LoreItemStackMetadata loreItemStackMetadata = (LoreItemStackMetadata) metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.LORE);
        CompoundBinaryTag displayCompoundBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
        List<String> lore = loreItemStackMetadata.getMetadataValue();
        if (!lore.isEmpty()) {
            ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
            lore.forEach(s -> listBinaryTag.add(new StringBinaryTag(s)));
            displayCompoundBinaryTag.putBinaryTag("Lore", listBinaryTag);
        }
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("display")) return;
        CompoundBinaryTag display = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        if (!display.containsKey("Lore")) return;
        ListBinaryTag lore = (ListBinaryTag) display.getBinaryTag("Lore");
        LoreItemStackMetadata loreItemStackMetadata = new LoreItemStackMetadata(new ArrayList<>());
        lore.forEach(binaryTag -> loreItemStackMetadata.getMetadataValue().add((String) binaryTag.getValue()));
        metadataKeyValueBucket.setMetadataKeyValue(loreItemStackMetadata);
    }
}
