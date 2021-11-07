package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityHeadLook extends PacketOut {

    private final Entity entity;

    public PacketOutEntityHeadLook(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        byteMessage.writeByte((byte) (entity.getLocation().getYaw()  * 256 / 360));
    }
}
