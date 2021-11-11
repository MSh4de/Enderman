package eu.mshade.enderman.itemstack.rewriters;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackManager;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.entities.LeatherArmorItemStack;
import eu.mshade.enderframe.mojang.Color;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class LeatherClothItemStackRewriter extends CommonItemStackRewriter {

    @Override
    public CompoundBinaryTag write(ItemStackManager itemStackManager, ItemStack itemStack) {
        LeatherArmorItemStack leatherArmorItemStack = (LeatherArmorItemStack) itemStack;
        CompoundBinaryTag compoundBinaryTag = super.write(itemStackManager, itemStack);
        CompoundBinaryTag displayBinaryTag = new CompoundBinaryTag();
        displayBinaryTag.putInt("color", leatherArmorItemStack.getColor().asRGB());
        displayBinaryTag.putString("Name", leatherArmorItemStack.getDisplayName().getText());
        compoundBinaryTag.putBinaryTag("display", displayBinaryTag);
        return compoundBinaryTag;
    }

    @Override
    public ItemStack read(ItemStackManager itemStackManager, Material material, int count, int durability, CompoundBinaryTag compoundBinaryTag) {
        LeatherArmorItemStack leatherArmorItemStack = new LeatherArmorItemStack(material, count, durability);
        CompoundBinaryTag displayBinaryTag = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("display");
        leatherArmorItemStack.setColor(Color.fromRGB(displayBinaryTag.getInt("color")));
        leatherArmorItemStack.setDisplayName(TextComponent.of(displayBinaryTag.getString("Name")));
        return leatherArmorItemStack;
    }
}
