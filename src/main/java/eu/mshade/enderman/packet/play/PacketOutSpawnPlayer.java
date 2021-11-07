package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

import java.util.concurrent.ThreadLocalRandom;

public class PacketOutSpawnPlayer extends PacketOut {

    private final Player player;

    public PacketOutSpawnPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(player.getEntityId());
        byteMessage.writeUUID(player.getUniqueId());
        byteMessage.writeInt((int) player.getLocation().getX() * 32);
        byteMessage.writeInt((int) player.getLocation().getY() * 32);
        byteMessage.writeInt((int) player.getLocation().getZ() * 32);
        byteMessage.writeByte((byte) (player.getLocation().getYaw() * 256 / 360));
        byteMessage.writeByte((byte) (player.getLocation().getPitch() * 256 / 360));
        byteMessage.writeShort(0);
        byteMessage.writeByte(0x7F);
    }

}
