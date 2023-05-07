package eu.mshade.enderman;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.item.*;
import eu.mshade.enderframe.metadata.*;
import eu.mshade.enderframe.metadata.entity.EntityMetadataBucket;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.wrapper.ContextWrapper;
import eu.mshade.enderframe.wrapper.MaterialKeyWrapper;
import eu.mshade.enderframe.wrapper.MaterialWrapperContext;
import eu.mshade.enderframe.wrapper.Wrapper;
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager;
import eu.mshade.enderman.metadata.EndermanItemStackManager;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class EndermanMinecraftByteBuf extends MinecraftByteBuf {

    private static Logger LOGGER = LoggerFactory.getLogger(EndermanMinecraftByteBuf.class);

    private final EndermanEntityMetadataManager entityMetadataManager;
    private final EndermanItemStackManager itemStackManager;
    private final MaterialKeyWrapper materialKeyWrapper;

    public EndermanMinecraftByteBuf(EndermanMinecraftProtocol minecraftProtocol, ByteBuf byteBuf) {
        super(byteBuf);
        this.entityMetadataManager = minecraftProtocol.getEntityMetadataManager();
        this.itemStackManager = minecraftProtocol.getItemStackManager();
        this.materialKeyWrapper = (MaterialKeyWrapper) minecraftProtocol.getWrapperRepository().get(ContextWrapper.MATERIAL_KEY);
    }


    @Override
    public void writeItemStack(ItemStack itemStack) {
        MaterialKey materialKey;
        if (itemStack == null || (materialKey = materialKeyWrapper.map(MaterialWrapperContext.ITEM, itemStack.getMaterial())) == null) {
            writeShort(-1);
            return;
        }
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        MetadataKeyValueBucket metadataKeyValueBucket = itemStack.getMetadataKeyValueBucket();
        for (MetadataKeyValue<?> metadataKeyValue : metadataKeyValueBucket.getMetadataKeyValues()) {
            if (itemStackManager.hasBuffer(metadataKeyValue.getMetadataKey())) {
                itemStackManager.getItemStackMetadataBuffer(metadataKeyValue.getMetadataKey()).write(compoundBinaryTag, itemStack);
            }
        }
        writeShort(materialKey.getId());
        writeByte(itemStack.getAmount() & 255);
        if (materialKey.inMaterialCategories(MaterialCategory.DURABILITY)) {
            writeShort(itemStack.getDurability());
        } else {
            writeShort(materialKey.getMetadata());
        }
        writeCompoundBinaryTag(compoundBinaryTag);

    }

    @Override
    public ItemStack readItemStack() {
        int id = readShort();
        if (id == -1) return new ItemStack(Material.AIR, 1, 0);

        byte count = readByte();
        int durability = readShort();
        MaterialKey parent = materialKeyWrapper.reverseMap(MaterialWrapperContext.ITEM, MaterialKey.from(id));
        MaterialKey materialKey;
        if (parent != null && parent.inMaterialCategories(MaterialCategory.DURABILITY)) {
            materialKey = parent;
        } else {
            materialKey = materialKeyWrapper.reverseMap(MaterialWrapperContext.ITEM, MaterialKey.from(id, durability));
        }
        ItemStack itemStack = new ItemStack(materialKey, count, durability);
        CompoundBinaryTag compoundBinaryTag = readCompoundBinaryTag();
        for (ItemStackMetadataWrapper itemStackMetadataWrapper : itemStackManager.getItemStackMetadataBuffers()) {
            itemStackMetadataWrapper.read(compoundBinaryTag, itemStack);
        }
        return itemStack;

    }

    @Override
    public void writeEntityMetadata(Entity entity, MetadataKey... entityMetadataKeys) {
        for (MetadataKey entityMetadataKey : entityMetadataKeys) {
            if (!entity.getMetadata().hasMetadataKeyValue(entityMetadataKey))
                continue;

            EntityMetadataBucket entityMetadataBucket = entityMetadataManager.getEntityMetadataBucket(entity);
            MetadataWrapper<Entity> entityMetadataWrapper = entityMetadataBucket.getEntityMetadataWrapper(entityMetadataKey);
            if (entityMetadataWrapper == null)
                continue;

            Metadata<?> metadata = entityMetadataWrapper.wrap(entity);
            int i = (entityMetadataManager.getMetadataIndex(metadata.getMetadataType())) << 5 | entityMetadataBucket.getIndexEntityMetadata(entityMetadataKey);
            this.writeByte(i);
            MetadataBuffer<Metadata<?>> metadataBuffer = (MetadataBuffer<Metadata<?>>) entityMetadataManager.getMetadataBuffer(metadata.getMetadataType());
            metadataBuffer.write(this, metadata);
        }
    }

    @Override
    public Collection<MetadataKey> getSupportedMetadataKeys(Entity entity) {
        EntityMetadataBucket entityMetadataBucket = entityMetadataManager.getEntityMetadataBucket(entity);
        return entityMetadataBucket.getEntityMetadataTypes();
    }
}
