package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataWrapper;
import eu.mshade.enderframe.item.metadata.LoreItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.StringBinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.util.ArrayList;
import java.util.List;

public class LoreItemStackMetadataWrapper implements ItemStackMetadataWrapper {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        LoreItemStackMetadata loreItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.LORE, LoreItemStackMetadata.class);
        CompoundBinaryTag displayCompoundBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
        List<TextComponent> lore = loreItemStackMetadata.getMetadataValue();
        if (!lore.isEmpty()) {
            ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
            lore.forEach(s -> listBinaryTag.add(new StringBinaryTag(s.toLegacyText())));
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
        lore.getValue().forEach(binaryTag -> loreItemStackMetadata.getMetadataValue().add(TextComponent.of((String) binaryTag.getValue())));
        metadataKeyValueBucket.setMetadataKeyValue(loreItemStackMetadata);
    }
}
