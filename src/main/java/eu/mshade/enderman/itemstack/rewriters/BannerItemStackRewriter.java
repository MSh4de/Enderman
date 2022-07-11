package eu.mshade.enderman.itemstack.rewriters;

import eu.mshade.enderframe.item.*;
import eu.mshade.enderframe.item.entities.BannerItemStack;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

public class BannerItemStackRewriter extends CommonItemStackRewriter {

    @Override
    public CompoundBinaryTag write(ItemStackManager itemStackManager, ItemStack itemStack) {
        CompoundBinaryTag compoundBinaryTag = super.write(itemStackManager, itemStack);
        if(itemStack instanceof BannerItemStack) {
            BannerItemStack bannerItemStack = (BannerItemStack)itemStack;
            CompoundBinaryTag blockEntityTag = new CompoundBinaryTag();
            ListBinaryTag patterns = new ListBinaryTag(BinaryTagType.COMPOUND);
            bannerItemStack.getPatterns().forEach(pattern -> {
                CompoundBinaryTag patternTag = new CompoundBinaryTag();
                patternTag.putInt("Color", pattern.getBannerColor().getDyeData());
                patternTag.putString("Pattern", pattern.getBannerPattern().getIdentifier());
                patterns.add(patternTag);
            });
            blockEntityTag.putBinaryTag("Patterns",patterns);

            compoundBinaryTag.putBinaryTag("BlockEntityTag", blockEntityTag);
            return compoundBinaryTag;
        }
        return compoundBinaryTag;
    }

    @Override
    public ItemStack read(ItemStackManager itemStackManager, MaterialKey material, int count, int durability, CompoundBinaryTag compoundBinaryTag) {
        BannerItemStack bannerItemStack = new BannerItemStack(material, count, durability);

        CompoundBinaryTag blockEntityTag = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("BlockEntityTag");
        ListBinaryTag patterns = (ListBinaryTag) blockEntityTag.getBinaryTag("Patterns");
        patterns.forEach(binaryTag -> {
            CompoundBinaryTag pattern = (CompoundBinaryTag) binaryTag;
            bannerItemStack.addPattern(new Pattern(BannerColor.getByDyeData((byte) pattern.getInt("Color")), BannerPattern.getByIdentifier(pattern.getString("Pattern"))));
        });
        return bannerItemStack;
    }
}
