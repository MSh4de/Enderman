package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Location;

public class MinecraftPacketOutEntityLook implements MinecraftPacketOut {

    private final Entity entity;
    private final Location location;

    public MinecraftPacketOutEntityLook(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId());
        minecraftByteBuf.writeByte((byte) (location.getYaw() * 256 / 360));
        minecraftByteBuf.writeByte((byte) (location.getPitch() * 256 / 360));
        minecraftByteBuf.writeBoolean(false);
    }
}
