package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.item.metadata.CanPlaceOnItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.Wrapper;
import eu.mshade.enderman.wrapper.EndermanNamespacedKeyWrapper;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

import java.util.ArrayList;
import java.util.List;

public class CanPlaceOnItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    private Wrapper<MaterialKey, NamespacedKey> endermanNamespacedKeyWrapper;

    public CanPlaceOnItemStackMetadataBuffer(Wrapper<MaterialKey, NamespacedKey> endermanNamespacedKeyWrapper) {
        this.endermanNamespacedKeyWrapper = endermanNamespacedKeyWrapper;
    }

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        CanPlaceOnItemStackMetadata canPlaceOnItemStackMetadata = (CanPlaceOnItemStackMetadata) metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_PLACE_ON);
        List<MaterialKey> materialKeys = canPlaceOnItemStackMetadata.getMetadataValue();
        ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
        materialKeys.forEach(materialKey -> {
            NamespacedKey namespacedKey = endermanNamespacedKeyWrapper.wrap(materialKey);
            if (namespacedKey != null) {
                listBinaryTag.add(new StringBinaryTag(namespacedKey.toString()));
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
