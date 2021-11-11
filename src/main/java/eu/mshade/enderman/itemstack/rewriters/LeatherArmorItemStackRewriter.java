package eu.mshade.enderman.itemstack.rewriters;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackManager;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.entities.LeatherArmorItemStack;
import eu.mshade.enderframe.mojang.Color;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class LeatherArmorItemStackRewriter extends CommonItemStackRewriter {

    @Override
    public CompoundBinaryTag write(ItemStackManager itemStackManager, ItemStack itemStack) {
        CompoundBinaryTag compoundBinaryTag = super.write(itemStackManager, itemStack);
        if(itemStack instanceof LeatherArmorItemStack) {
            LeatherArmorItemStack leatherArmorItemStack = (LeatherArmorItemStack) itemStack;
            CompoundBinaryTag displayBinaryTag = compoundBinaryTag.computeIfAbsent("display", s -> new CompoundBinaryTag());
            displayBinaryTag.putInt("color", leatherArmorItemStack.getColor().asRGB());
            return compoundBinaryTag;
        }
        return compoundBinaryTag;
    }

    @Override
    public ItemStack read(ItemStackManager itemStackManager, Material material, int count, int durability, CompoundBinaryTag compoundBinaryTag) {
        LeatherArmorItemStack leatherArmorItemStack = new LeatherArmorItemStack(material, count, durability);
        CompoundBinaryTag displayBinaryTag = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        leatherArmorItemStack.setColor(Color.fromRGB(displayBinaryTag.getInt("color")));
        return leatherArmorItemStack;
    }
}
