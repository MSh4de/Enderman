package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityHeadLook extends PacketOut {

    private final Entity entity;
    private final float rotation;

    public PacketOutEntityHeadLook(Entity entity, float rotation) {
        this.entity = entity;
        this.rotation = rotation;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte((int) rotation);
    }
}
