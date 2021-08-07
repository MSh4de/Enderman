package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

import java.util.List;

import static org.json.XMLTokener.entity;

public class PacketOutDestroyEntities extends PacketOut {
    private final Entity[] entities;

    public PacketOutDestroyEntities(Entity...entities) {
        this.entities = entities;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entities.length);
        for(Entity entity : entities) {
            byteMessage.writeVarInt(entity.getEntityId());
        }
    }
}
