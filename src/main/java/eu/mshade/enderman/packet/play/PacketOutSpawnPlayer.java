package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Position;

import java.util.UUID;

public class PacketOutSpawnPlayer extends PacketOut {

    private int id;
    private UUID uuid;
    private Position position;

    public PacketOutSpawnPlayer(int id, UUID uuid, Position position) {
        this.id = id;
        this.uuid = uuid;
        this.position = position;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeUUID(uuid);
        byteMessage.writeInt((int) position.getX());
        byteMessage.writeInt((int) position.getZ());
        byteMessage.writeInt((int) position.getY());
        byteMessage.writeByte((int) position.getYaw());
        byteMessage.writeByte((int) position.getPitch());
        byteMessage.writeShort(0);
        byteMessage.writeByte(0xff);
    }
}
