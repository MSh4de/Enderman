package eu.mshade.enderman.packet.play.world

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutTimeUpdate(private val timeOfDay: Long) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeLong(0)
        minecraftByteBuf.writeLong(timeOfDay)
    }
}