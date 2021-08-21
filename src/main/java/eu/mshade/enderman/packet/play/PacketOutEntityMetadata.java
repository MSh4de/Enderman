package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityMetadata extends PacketOut {

    private final Entity entity;
    private final MetadataMeaning[] metadataMeanings;

    public PacketOutEntityMetadata(Entity entity, MetadataMeaning... metadataMeanings) {
        this.entity = entity;
        this.metadataMeanings = metadataMeanings;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(entity.getEntityId());
        for (MetadataMeaning metadataMeaning : metadataMeanings) byteMessage.writeMetadata(entity, metadataMeaning);
        byteMessage.writeByte(127);
    }
}
