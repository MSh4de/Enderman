package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.attribute.AttributeProperty;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

import java.util.List;

public class MinecraftPacketOutEntityProperties implements MinecraftPacketOut {
    private final int id;
    private final List<AttributeProperty> attributeProperties;

    public MinecraftPacketOutEntityProperties(int id, List<AttributeProperty> attributeProperties) {
        this.id = id;
        this.attributeProperties = attributeProperties;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(id);
        minecraftByteBuf.writeInt(attributeProperties.size());
        attributeProperties.forEach(attributeProperty -> {
            minecraftByteBuf.writeString(attributeProperty.getAttribute().getName());
            minecraftByteBuf.writeDouble(attributeProperty.getValue());
            minecraftByteBuf.writeVarInt(0);
        });
    }
}