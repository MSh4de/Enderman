package eu.mshade.enderman.packet.play.animation

import eu.mshade.enderframe.animation.AnimationType
import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutAnimation(private val player: Player, private val animationType: AnimationType) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(player.getEntityId())
        minecraftByteBuf.writeVarInt(animationType.value)
    }
}