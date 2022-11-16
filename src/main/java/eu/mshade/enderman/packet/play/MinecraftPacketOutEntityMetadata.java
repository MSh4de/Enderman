package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutEntityMetadata implements MinecraftPacketOut {

    private final Entity entity;
    private final EntityMetadataKey[] entityMetadataKeys;

    public MinecraftPacketOutEntityMetadata(Entity entity, EntityMetadataKey... entityMetadataKeys) {
        this.entity = entity;
        this.entityMetadataKeys = entityMetadataKeys;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        for (EntityMetadataKey entityMetadataKey : entityMetadataKeys) minecraftByteBuf.writeEntityMetadata(entity, entityMetadataKey);
        minecraftByteBuf.writeByte(127);
    }
}
