package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutEntityRelativeMove implements MinecraftPacketOut {

    private final Entity entity;
    private final byte x, y, z;

    public MinecraftPacketOutEntityRelativeMove(Entity entity, byte x, byte y, byte z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        protocolBuffer.writeByte(x);
        protocolBuffer.writeByte(y);
        protocolBuffer.writeByte(z);
        protocolBuffer.writeBoolean(false);
    }
}
