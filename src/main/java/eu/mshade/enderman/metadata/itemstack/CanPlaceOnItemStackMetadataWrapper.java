package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataWrapper;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.item.metadata.CanPlaceOnItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.Wrapper;
import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.StringBinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.util.ArrayList;
import java.util.List;

public class CanPlaceOnItemStackMetadataWrapper implements ItemStackMetadataWrapper {

    private Wrapper<MaterialKey, NamespacedKey> endermanNamespacedKeyWrapper;

    public CanPlaceOnItemStackMetadataWrapper(Wrapper<MaterialKey, NamespacedKey> endermanNamespacedKeyWrapper) {
        this.endermanNamespacedKeyWrapper = endermanNamespacedKeyWrapper;
    }

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        CanPlaceOnItemStackMetadata canPlaceOnItemStackMetadata = (CanPlaceOnItemStackMetadata) metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_PLACE_ON);
        List<MaterialKey> materialKeys = canPlaceOnItemStackMetadata.getMetadataValue();
        ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
        materialKeys.forEach(materialKey -> {
            NamespacedKey namespacedKey = endermanNamespacedKeyWrapper.map(materialKey);
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
        for (BinaryTag<?> binaryTag : canPlaceOn.getValue()) {
            NamespacedKey namespacedKey = NamespacedKey.fromString((String) binaryTag.getValue());
            canPlaceOnItemStackMetadata.getMetadataValue().add(endermanNamespacedKeyWrapper.reverseMap(namespacedKey));
        }
        metadataKeyValueBucket.setMetadataKeyValue(canPlaceOnItemStackMetadata);
    }
}
