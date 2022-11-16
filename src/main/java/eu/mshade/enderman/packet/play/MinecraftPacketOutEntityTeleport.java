package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutEntityTeleport implements MinecraftPacketOut {

    private final Entity entity;
    private final boolean onGround;

    private final int x, y, z, yaw, pitch;

    public MinecraftPacketOutEntityTeleport(Entity entity, int x, int y, int z, int yaw, int pitch, boolean onGround) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        minecraftByteBuf.writeInt(x);
        minecraftByteBuf.writeInt(y);
        minecraftByteBuf.writeInt(z);
        minecraftByteBuf.writeByte(yaw);
        minecraftByteBuf.writeByte(pitch);
        minecraftByteBuf.writeBoolean(onGround);
    }

}
