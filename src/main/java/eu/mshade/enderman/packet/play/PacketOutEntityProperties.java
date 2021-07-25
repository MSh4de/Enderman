package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.attribute.AttributeModifier;
import eu.mshade.enderframe.metadata.attribute.AttributeProperty;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

import java.util.ArrayList;
import java.util.List;

public class PacketOutEntityProperties extends PacketOut {
    private final int id;
    private final List<AttributeProperty> attributeProperties;

    public PacketOutEntityProperties(int id, List<AttributeProperty> attributeProperties) {
        this.id = id;
        this.attributeProperties = attributeProperties;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeInt(attributeProperties.size());
        attributeProperties.forEach(attributeProperty -> {
            byteMessage.writeString(attributeProperty.getAttribute().getName());
            byteMessage.writeDouble(attributeProperty.getValue());
            byteMessage.writeVarInt(0);
        });
    }
}