package eu.mshade.enderman.packet.play.world

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut
import eu.mshade.enderframe.world.Difficulty

class MinecraftPacketOutServerDifficulty(private val difficulty: Difficulty) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeByte(difficulty.id)
    }

}