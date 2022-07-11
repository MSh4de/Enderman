package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutDestroyEntities implements PacketOut {
    private final Entity[] entities;

    public PacketOutDestroyEntities(Entity...entities) {
        this.entities = entities;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(entities.length);
        for(Entity entity : entities) {
            protocolBuffer.writeVarInt(entity.getEntityId());
        }
    }
}
