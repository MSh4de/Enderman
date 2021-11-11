package eu.mshade.enderman.itemstack.rewriters;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackManager;
import eu.mshade.enderframe.item.ItemStackRewriter;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.mojang.NamespacedKey;
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
    public ItemStack read(ItemStackManager itemStackManager, Material material, int count, int durability, CompoundBinaryTag compoundBinaryTag) {
        ItemStack itemStack = new ItemStack(material, count, durability);
        itemStack.setUnbreakable(compoundBinaryTag.getByte("Unbreakable") == 1);

        ListBinaryTag canDestroy = (ListBinaryTag) compoundBinaryTag.getBinaryTag("CanDestroy");

        canDestroy.forEach(adventureBlock -> itemStack.addAdventureBlocks(itemStackManager.getMaterialFromNamespacedKey(NamespacedKey.fromString(((StringBinaryTag)adventureBlock).getValue()))));

        ListBinaryTag enc = (ListBinaryTag) compoundBinaryTag.getBinaryTag("ench");
        enc.forEach(enchantment-> {
            CompoundBinaryTag enchant = (CompoundBinaryTag) enchantment;
            itemStack.addEnchantment(itemStackManager.getEnchantmentFromWrap(enchant.getShort("id")), enchant.getShort("lvl"));
        });
        return itemStack;
    }
}
