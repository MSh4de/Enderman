package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.attribute.AttributeProperty;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

import java.util.List;

public class PacketOutEntityProperties implements PacketOut {
    private final int id;
    private final List<AttributeProperty> attributeProperties;

    public PacketOutEntityProperties(int id, List<AttributeProperty> attributeProperties) {
        this.id = id;
        this.attributeProperties = attributeProperties;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(id);
        protocolBuffer.writeInt(attributeProperties.size());
        attributeProperties.forEach(attributeProperty -> {
            protocolBuffer.writeString(attributeProperty.getAttribute().getName());
            protocolBuffer.writeDouble(attributeProperty.getValue());
            protocolBuffer.writeVarInt(0);
        });
    }
}