package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutSpawnMob implements MinecraftPacketOut {
    private final int id;
    private final Entity entity;

    public MinecraftPacketOutSpawnMob(int id, Entity entity) {
        this.id = id;
        this.entity = entity;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        minecraftByteBuf.writeByte(id);
        minecraftByteBuf.writeInt(entity.getLocation().getBlockX() * 32);
        minecraftByteBuf.writeInt(entity.getLocation().getBlockY() * 32);
        minecraftByteBuf.writeInt(entity.getLocation().getBlockZ() * 32);
        minecraftByteBuf.writeByte((byte) (entity.getLocation().getYaw() * 256 / 360));
        minecraftByteBuf.writeByte((byte) (entity.getLocation().getPitch() * 256 / 360));
        minecraftByteBuf.writeByte((byte) (entity.getLocation().getYaw() * 256 / 360));
        minecraftByteBuf.writeShort((int) entity.getVelocity().getX());
        minecraftByteBuf.writeShort((int) entity.getVelocity().getY());
        minecraftByteBuf.writeShort((int) entity.getVelocity().getZ());
        minecraftByteBuf.writeByte(0x7F);
    }

}
