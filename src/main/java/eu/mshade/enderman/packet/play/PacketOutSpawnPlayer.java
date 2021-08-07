package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutSpawnPlayer extends PacketOut {

    private final Player player;

    public PacketOutSpawnPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(player.getEntityId());
        byteMessage.writeUUID(player.getUUID());
        byteMessage.writeInt((int) player.getLocation().getX() * 32);
        byteMessage.writeInt((int) player.getLocation().getY() * 32);
        byteMessage.writeInt((int) player.getLocation().getZ() * 32);
        byteMessage.writeByte((byte) player.getLocation().getYaw());
        byteMessage.writeByte((byte) player.getLocation().getPitch());
        byteMessage.writeShort(0);
        byteMessage.writeByte(0x7F);
    }

}
