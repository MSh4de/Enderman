package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutEntityTeleport implements MinecraftPacketOut {

    private final Entity entity;
    private final boolean onGround;

    private final int x, y, z, yaw, pitch;

    public MinecraftPacketOutEntityTeleport(Entity entity, int x, int y, int z, int yaw, int pitch, boolean onGround) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entity.getEntityId());
        protocolBuffer.writeInt(x);
        protocolBuffer.writeInt(y);
        protocolBuffer.writeInt(z);
        protocolBuffer.writeByte(yaw);
        protocolBuffer.writeByte(pitch);
        protocolBuffer.writeBoolean(onGround);
    }

}
