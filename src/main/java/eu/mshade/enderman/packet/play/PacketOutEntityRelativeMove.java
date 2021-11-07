package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityRelativeMove extends PacketOut {

    private final Entity entity;
    private final byte x, y, z;

    public PacketOutEntityRelativeMove(Entity entity, byte x, byte y, byte z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte(x);
        byteMessage.writeByte(y);
        byteMessage.writeByte(z);
        byteMessage.writeBoolean(false);
    }
}
