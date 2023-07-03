package eu.mshade.enderman.packet.play.entity;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutEntityLookRelativeMove implements MinecraftPacketOut {

    private final Entity entity;
    private final byte x,y,z;
    private final int yaw,pitch;

    public MinecraftPacketOutEntityLookRelativeMove(Entity entity, byte x, byte y, byte z, int yaw, int pitch) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        minecraftByteBuf.writeByte(x);
        minecraftByteBuf.writeByte(y);
        minecraftByteBuf.writeByte(z);
        minecraftByteBuf.writeByte(yaw);
        minecraftByteBuf.writeByte(pitch);
        minecraftByteBuf.writeBoolean(false);
    }
}
