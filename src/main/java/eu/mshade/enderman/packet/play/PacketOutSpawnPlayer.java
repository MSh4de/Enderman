package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutSpawnPlayer implements PacketOut {

    private final Player player;

    public PacketOutSpawnPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(player.getEntityId());
        protocolBuffer.writeUUID(player.getUniqueId());
        protocolBuffer.writeInt((int) player.getLocation().getX() * 32);
        protocolBuffer.writeInt((int) player.getLocation().getY() * 32);
        protocolBuffer.writeInt((int) player.getLocation().getZ() * 32);
        protocolBuffer.writeByte((byte) (player.getLocation().getYaw() * 256 / 360));
        protocolBuffer.writeByte((byte) (player.getLocation().getPitch() * 256 / 360));
        protocolBuffer.writeShort(0);
        protocolBuffer.writeByte(0x7F);
    }

}
