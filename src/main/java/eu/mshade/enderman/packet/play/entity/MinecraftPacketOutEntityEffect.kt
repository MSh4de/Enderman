package eu.mshade.enderman.packet.play.entity

import eu.mshade.enderframe.effect.Effect
import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutEntityEffect(val entity: Entity, val effect: EndermanEffect): MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId())
        minecraftByteBuf.writeByte(effect.effectId)
        minecraftByteBuf.writeByte(effect.amplifier)
        minecraftByteBuf.writeVarInt(effect.duration)
        minecraftByteBuf.writeBoolean(effect.hideParticles)
    }

}

data class EndermanEffect(val effectId: Int, val amplifier: Int, val duration: Int, val hideParticles: Boolean)