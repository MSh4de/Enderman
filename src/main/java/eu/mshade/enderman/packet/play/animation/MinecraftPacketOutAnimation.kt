package eu.mshade.enderman.packet.play.animation

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutAnimation(private val entity: Entity, private val animationType: AnimationType) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId())
        minecraftByteBuf.writeVarInt(animationType.value)
    }
}