package eu.mshade.enderman.metadata.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackMetadataBuffer;
import eu.mshade.enderframe.item.metadata.SkullOwnerItemStackMetadata;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.GameProfile;
import eu.mshade.enderframe.mojang.Property;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.util.UUID;

public class SkullOwnerItemStackMetadataBuffer implements ItemStackMetadataBuffer {

    @Override
    public void write(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        SkullOwnerItemStackMetadata skullOwnerItemStackMetadata = metadataKeyValueBucket.getMetadataKeyValue(ItemStackMetadataKey.SKULL_OWNER, SkullOwnerItemStackMetadata.class);
        GameProfile gameProfile = skullOwnerItemStackMetadata.getMetadataValue();

        CompoundBinaryTag skullOwnerCompoundBinaryTag = new CompoundBinaryTag();
        skullOwnerCompoundBinaryTag.putString("id", gameProfile.getId().toString());
        skullOwnerCompoundBinaryTag.putString("Name", gameProfile.getName());

        CompoundBinaryTag propertiesCompoundBinaryTag = new CompoundBinaryTag();
        ListBinaryTag texturesListBinaryTag = new ListBinaryTag(BinaryTagType.COMPOUND);

        gameProfile.getProperties().forEach((s, property) -> {
            CompoundBinaryTag propertyBinaryTag = new CompoundBinaryTag();
            if (property.getSignature() != null) propertyBinaryTag.putString("Signature", property.getSignature());
            propertyBinaryTag.putString("Value", property.getValue());
            texturesListBinaryTag.add(propertyBinaryTag);
        });

        propertiesCompoundBinaryTag.putBinaryTag("textures", texturesListBinaryTag);
        skullOwnerCompoundBinaryTag.putBinaryTag("Properties", propertiesCompoundBinaryTag);

        compoundBinaryTag.putBinaryTag("SkullOwner", skullOwnerCompoundBinaryTag);
    }

    @Override
    public void read(CompoundBinaryTag compoundBinaryTag, ItemStack itemStack) {
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        if (!compoundBinaryTag.containsKey("SkullOwner")) return;
        CompoundBinaryTag skullOwner = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("SkullOwner");
        if (!skullOwner.containsKey("Properties")) return;
        CompoundBinaryTag properties = (CompoundBinaryTag) skullOwner.getBinaryTag("Properties");
        if (!properties.containsKey("textures")) return;
        ListBinaryTag textures = (ListBinaryTag) properties.getBinaryTag("textures");

        GameProfile gameProfile = new GameProfile(UUID.fromString(skullOwner.getString("id")), skullOwner.getString("Name"));
        textures.getValue().forEach(binaryTag ->{
            CompoundBinaryTag propertyBinaryTag = (CompoundBinaryTag) binaryTag;
            String signature = propertyBinaryTag.getString("Signature");
            String value = propertyBinaryTag.getString("Value");
            Property property = new Property("textures", value, signature);
            gameProfile.setProperty(property);
        });

        SkullOwnerItemStackMetadata skullOwnerItemStackMetadata = new SkullOwnerItemStackMetadata(gameProfile);
        metadataKeyValueBucket.setMetadataKeyValue(skullOwnerItemStackMetadata);

    }
}
