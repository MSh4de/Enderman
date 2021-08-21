package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Position;

import java.util.UUID;

public class PacketOutSpawnPlayer extends PacketOut {

    private int id;
    private UUID uuid;
    private int x, y, z;

    public PacketOutSpawnPlayer(int id, UUID uuid, int x, int y, int z) {
        this.id = id;
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeUUID(uuid);
        byteMessage.writeInt( x*32);
        byteMessage.writeInt( y*32);
        byteMessage.writeInt( z*32);
        byteMessage.writeByte(0);
        byteMessage.writeByte(0);
        byteMessage.writeShort(0);
        byteMessage.writeByte(127);
    }
}
