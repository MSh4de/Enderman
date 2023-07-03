package eu.mshade.enderman.packet.play.entity

import eu.mshade.enderframe.attribute.AttributeKey
import eu.mshade.enderframe.attribute.AttributeModifier
import eu.mshade.enderframe.attribute.AttributeProperty
import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut
import java.util.function.Consumer

class MinecraftPacketOutEntityProperties(
    private val entity: Entity,
    private val attributeProperties: List<EndermanAttributeProperty>
) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId())
        minecraftByteBuf.writeInt(attributeProperties.size)

        attributeProperties.forEach {attributeProperty ->

            minecraftByteBuf.writeString(attributeProperty.attribute)
            minecraftByteBuf.writeDouble(attributeProperty.value)
            minecraftByteBuf.writeVarInt(attributeProperty.attributeModifiers.size)

            attributeProperty.attributeModifiers.forEach{modifier ->
                minecraftByteBuf.writeUUID(modifier.uuid)
                minecraftByteBuf.writeDouble(modifier.amount)
                minecraftByteBuf.writeByte(modifier.attributeOperation.ordinal)
            }

        }
    }

}


data class EndermanAttributeProperty(val attribute: String, val value: Double, val attributeModifiers: List<AttributeModifier>)

