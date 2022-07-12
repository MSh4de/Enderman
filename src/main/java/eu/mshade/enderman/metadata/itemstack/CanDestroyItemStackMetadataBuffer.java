package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.item.metadata.CanDestroyItemStackMetadata;
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

public class CanDestroyItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    private EndermanNamespacedKeyWrapper endermanNamespacedKeyWrapper;

    public CanDestroyItemStackMetadataBuffer(EndermanNamespacedKeyWrapper endermanNamespacedKeyWrapper) {
        this.endermanNamespacedKeyWrapper = endermanNamespacedKeyWrapper;
    }

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        CanDestroyItemStackMetadata canDestroyItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.CAN_DESTROY, CanDestroyItemStackMetadata.class);
        List<MaterialKey> materialKeys = canDestroyItemStackMetadata.getMetadataValue();
        ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.STRING);
        materialKeys.forEach(materialKey -> {
            if (endermanNamespacedKeyWrapper.isSupport(materialKey)) {
                listBinaryTag.add(new StringBinaryTag(endermanNamespacedKeyWrapper.wrap(materialKey).toString()));
            }
        });
        compoundBinaryTag.putBinaryTag("CanDestroy", listBinaryTag);
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("CanDestroy")) return;
        ListBinaryTag canDestroy = (ListBinaryTag) compoundBinaryTag.getBinaryTag("CanDestroy");
        if (canDestroy.isEmpty()) return;
        CanDestroyItemStackMetadata canDestroyItemStackMetadata = new CanDestroyItemStackMetadata(new ArrayList<>());
        for (BinaryTag<?> binaryTag : canDestroy) {
            NamespacedKey namespacedKey = NamespacedKey.fromString((String) binaryTag.getValue());
            canDestroyItemStackMetadata.getMetadataValue().add(endermanNamespacedKeyWrapper.reverse(namespacedKey));
        }
        metadataKeyValueBucket.setMetadataKeyValue(canDestroyItemStackMetadata);
    }
}
