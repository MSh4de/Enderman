package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutEntityLookRelativeMove implements PacketOut  {

    private final Entity entity;
    private final byte x,y,z;
    private final int yaw,pitch;

    public PacketOutEntityLookRelativeMove(Entity entity, byte x, byte y, byte z, int yaw, int pitch) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        protocolBuffer.writeByte(x);
        protocolBuffer.writeByte(y);
        protocolBuffer.writeByte(z);
        protocolBuffer.writeByte(yaw);
        protocolBuffer.writeByte(pitch);
        protocolBuffer.writeBoolean(false);
    }
}
