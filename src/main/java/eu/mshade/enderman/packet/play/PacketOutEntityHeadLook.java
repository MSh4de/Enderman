package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Location;

public class PacketOutEntityHeadLook implements PacketOut {

    private final Entity entity;
    private final Location location;

    public PacketOutEntityHeadLook(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        protocolBuffer.writeByte((byte) (location.getYaw()  * 256 / 360));
    }
}
