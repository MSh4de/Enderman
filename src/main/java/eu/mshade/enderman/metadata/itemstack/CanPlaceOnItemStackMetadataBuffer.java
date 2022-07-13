package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.item.metadata.CanPlaceOnItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderman.wrapper.EndermanNamespacedKeyWrapper;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

import java.util.ArrayList;
import java.util.List;

public class CanPlaceOnItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    private EndermanNamespacedKeyWrapper endermanNamespacedKeyWrapper;

    public CanPlaceOnItemStackMetadataBuffer(EndermanNamespacedKeyWrapper endermanNamespacedKeyWrapper) {
        this.endermanNamespacedKeyWrapper = endermanNamespacedKeyWrapper;
    }

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        CanPlaceOnItemStackMetadata canPlaceOnItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_PLACE_ON, CanPlaceOnItemStackMetadata.class);
        List<MaterialKey> materialKeys = canPlaceOnItemStackMetadata.getMetadataValue();
        ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
        materialKeys.forEach(materialKey -> {
            if (endermanNamespacedKeyWrapper.isSupport(materialKey)) {
                listBinaryTag.add(new StringBinaryTag(endermanNamespacedKeyWrapper.wrap(materialKey).toString()));
            }
        });
        compoundBinaryTag.putBinaryTag("CanPlaceOn", listBinaryTag);

    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("CanPlaceOn")) return;
        ListBinaryTag canPlaceOn = (ListBinaryTag) compoundBinaryTag.getBinaryTag("CanPlaceOn");
        if (canPlaceOn.isEmpty()) return;
        CanPlaceOnItemStackMetadata canPlaceOnItemStackMetadata = new CanPlaceOnItemStackMetadata(new ArrayList<>());
        for (BinaryTag<?> binaryTag : canPlaceOn) {
            NamespacedKey namespacedKey = NamespacedKey.fromString((String) binaryTag.getValue());
            canPlaceOnItemStackMetadata.getMetadataValue().add(endermanNamespacedKeyWrapper.reverse(namespacedKey));
        }
        metadataKeyValueBucket.setMetadataKeyValue(canPlaceOnItemStackMetadata);
    }
}
