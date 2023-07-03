package eu.mshade.enderman.packet.play.entity;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutEntityRelativeMove implements MinecraftPacketOut {

    private final Entity entity;
    private final byte x, y, z;

    public MinecraftPacketOutEntityRelativeMove(Entity entity, byte x, byte y, byte z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        minecraftByteBuf.writeByte(x);
        minecraftByteBuf.writeByte(y);
        minecraftByteBuf.writeByte(z);
        minecraftByteBuf.writeBoolean(false);
    }
}
