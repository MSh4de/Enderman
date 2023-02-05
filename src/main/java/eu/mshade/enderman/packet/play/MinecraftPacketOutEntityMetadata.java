package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataKey;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

import java.util.Collection;

public class MinecraftPacketOutEntityMetadata implements MinecraftPacketOut {

    private final Entity entity;
    private final Collection<MetadataKey> metadataKeys;

    public MinecraftPacketOutEntityMetadata(Entity entity, Collection<MetadataKey> metadataKeys) {
        this.entity = entity;
        this.metadataKeys = metadataKeys;
    }


    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        for (MetadataKey entityMetadataKey : metadataKeys) {
            minecraftByteBuf.writeEntityMetadata(entity, entityMetadataKey);
        }
        minecraftByteBuf.writeByte(127);
    }
}
