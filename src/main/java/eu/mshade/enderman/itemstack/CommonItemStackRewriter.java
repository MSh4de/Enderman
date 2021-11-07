package eu.mshade.enderman.itemstack;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackManager;
import eu.mshade.enderframe.item.ItemStackRewriter;
import eu.mshade.enderframe.item.Material;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

import java.util.List;

public class CommonItemStackRewriter implements ItemStackRewriter {

    @Override
    public CompoundBinaryTag write(ItemStackManager itemStackManager, ItemStack itemStack) {
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        compoundBinaryTag.putByte("Unbreakable", itemStack.isUnbreakable() ? (byte) 1 : (byte) 0);
        if (!itemStack.getAdventureBlocks().isEmpty()) {
            ListBinaryTag canDestroy = new ListBinaryTag(BinaryTagType.STRING);
            for (Material adventureBlock : itemStack.getAdventureBlocks()) {
                canDestroy.add(new StringBinaryTag(itemStackManager.getNamespacedKeyFromMaterial(adventureBlock).toString()));
            }
            compoundBinaryTag.putBinaryTag("CanDestroy", canDestroy);
        }

        if (itemStack.hasEnchants()) {
            ListBinaryTag ench = new ListBinaryTag(BinaryTagType.COMPOUND);
            itemStack.getEnchantments().forEach((enchantment, level) -> {
                CompoundBinaryTag enchant = new CompoundBinaryTag();
                enchant.putShort("id", (short) itemStackManager.getWrapFromEnchantment(enchantment));

                enchant.putShort("lvl", level.shortValue());
            });
            compoundBinaryTag.putBinaryTag("ench", ench);
        }


        return compoundBinaryTag;
    }

    @Override
    public ItemStack read(ItemStackManager itemStackManager, CompoundBinaryTag compoundBinaryTag) {
        return null;
    }
}
