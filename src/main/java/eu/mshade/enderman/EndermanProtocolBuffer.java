package eu.mshade.enderman;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.metadata.*;
import eu.mshade.enderframe.metadata.entity.EntityMetadataBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderman.itemstack.EndermanItemStackManager;
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import io.netty.buffer.ByteBuf;

public class EndermanProtocolBuffer extends ProtocolBuffer {

    private final EndermanEntityMetadataManager entityMetadataManager;
    private final EndermanItemStackManager itemStackManager;
    private EndermanMaterialWrapper endermanMaterialWrapper;

    public EndermanProtocolBuffer(EndermanEntityMetadataManager entityMetadataManager, EndermanItemStackManager itemStackManager, EndermanMaterialWrapper endermanMaterialWrapper, ByteBuf byteBuf) {
        super(byteBuf);
        this.entityMetadataManager = entityMetadataManager;
        this.itemStackManager = itemStackManager;
        this.endermanMaterialWrapper = endermanMaterialWrapper;
    }




    @Override
    public void writeItemStack(ItemStack itemStack) {

    }

    @Override
    public ItemStack readItemStack() {
        int id = readShort();
        if(id != -1) {
            byte count = readByte();
            int durability = readShort();
            MaterialKey material = endermanMaterialWrapper.reverse(MaterialKey.from(id, durability));
            CompoundBinaryTag compoundBinaryTag = readCompoundBinaryTag();

            return new ItemStack(material, count, durability);

            //return itemStackManager.getItemStackRewriterByMaterial(material).read(itemStackManager, material, count, durability, compoundBinaryTag);
        }
        return new ItemStack(Material.AIR,1,0);

    }

    @Override
    public void writeEntityMetadata(Entity entity, EntityMetadataKey... entityMetadataKeys) {
        for (EntityMetadataKey entityMetadataKey : entityMetadataKeys) {
            EntityMetadataBucket entityMetadataBucket = entityMetadataManager.getEntityMetadataBucket(entity);
            MetadataWrapper<Entity> entityMetadataBuffer = entityMetadataBucket.getEntityMetadataBuffer(entityMetadataKey);
            Metadata<?> metadata = entityMetadataBuffer.wrap(entity);
            int i = (entityMetadataManager.getMetadataIndex(metadata.getMetadataType())) << 5 | entityMetadataBucket.getIndexEntityMetadata(entityMetadataKey);
            this.writeByte(i);
            MetadataBuffer<Metadata<?>> metadataBuffer = (MetadataBuffer<Metadata<?>>) entityMetadataManager.getMetadataBuffer(metadata.getMetadataType());
            metadataBuffer.write(this, metadata);
        }
    }


}
