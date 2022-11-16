package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutDestroyEntities implements MinecraftPacketOut {
    private final Entity[] entities;

    public MinecraftPacketOutDestroyEntities(Entity...entities) {
        this.entities = entities;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entities.length);
        for(Entity entity : entities) {
            minecraftByteBuf.writeVarInt(entity.getEntityId());
        }
    }
}
