package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.metadata.MetadataKey;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutSpawnPlayer implements MinecraftPacketOut {

    private final Player player;

    public MinecraftPacketOutSpawnPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(player.getEntityId());
        minecraftByteBuf.writeUUID(player.getUniqueId());
        minecraftByteBuf.writeInt((int) player.getLocation().getX() * 32);
        minecraftByteBuf.writeInt((int) player.getLocation().getY() * 32);
        minecraftByteBuf.writeInt((int) player.getLocation().getZ() * 32);
        minecraftByteBuf.writeByte((byte) (player.getLocation().getYaw() * 256 / 360));
        minecraftByteBuf.writeByte((byte) (player.getLocation().getPitch() * 256 / 360));
        minecraftByteBuf.writeShort(0);
        for (MetadataKey entityMetadataKey : player.getMetadata().getMetadataKeys()) {
            minecraftByteBuf.writeEntityMetadata(player, entityMetadataKey);
        }
        minecraftByteBuf.writeByte(127);
    }

}
