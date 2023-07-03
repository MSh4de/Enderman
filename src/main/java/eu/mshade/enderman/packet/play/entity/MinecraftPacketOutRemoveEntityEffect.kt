package eu.mshade.enderman.packet.play.entity

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutRemoveEntityEffect(val entity: Entity, private val effect: Int) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId())
        minecraftByteBuf.writeByte(effect)
    }

}