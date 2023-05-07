package eu.mshade.enderman.packet.play.animation

import eu.mshade.enderframe.entity.Player
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut
import eu.mshade.enderframe.world.Vector

class MinecraftPacketOutBlockBreak(val player: Player, val blockPosition: Vector, val breakStage: Int) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(player.getEntityId())
        minecraftByteBuf.writeBlockPosition(blockPosition)
        minecraftByteBuf.writeByte(breakStage)
    }
}