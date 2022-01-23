package eu.mshade.enderman;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Item;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.ItemStackRewriter;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderman.itemstack.EndermanItemStackManager;
import eu.mshade.enderman.itemstack.MaterialData;
import eu.mshade.enderman.metadata.EndermanMetadataManager;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import io.netty.buffer.ByteBuf;

public class EndermanByteMessage extends ByteMessage {

    private static final MetadataManager metadataManager = new EndermanMetadataManager();
    private static final EndermanItemStackManager itemStackManager = new EndermanItemStackManager();

    public EndermanByteMessage(ByteBuf buf) {
        super(buf);
    }

    @Override
    public void writeMetadata(Entity entity, MetadataMeaning metadataMeaning) {
        metadataManager.write(this, entity, metadataMeaning);
    }

    @Override
    public void writeItemStack(ItemStack itemStack) {
        if(itemStack == null || !itemStackManager.hasWrapByMaterial(itemStack.getMaterial())){
            writeShort(-1);
            return;
        }
        MaterialData wrapByMaterial = itemStackManager.getWrapByMaterial(itemStack.getMaterial());
        writeShort(wrapByMaterial.getId());
        writeByte(itemStack.getCount() & 255);
        writeShort(wrapByMaterial.getData());
        ItemStackRewriter itemStackRewriterByMaterial = itemStackManager.getItemStackRewriterByMaterial(itemStack.getMaterial());
        writeCompoundTag(itemStackRewriterByMaterial.write(itemStackManager, itemStack));
    }

    @Override
    public ItemStack readItemStack() {
        int id = readShort();
        if(id != -1) {
            byte count = readByte();
            int durability = readShort();
            Material material = itemStackManager.getMaterialByWrap(new MaterialData(id, durability));
            CompoundBinaryTag compoundBinaryTag = readCompoundTag();

            return itemStackManager.getItemStackRewriterByMaterial(material).read(itemStackManager, material, count, durability, compoundBinaryTag);
        }
        return new ItemStack(Material.AIR,1,0);
    }
}
