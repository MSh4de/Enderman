package eu.mshade.enderman.packet.play.chat

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutTabComplete(private val matches: Array<String>) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(matches.size)
        for (match in matches) {
            minecraftByteBuf.writeString(match)
        }
    }
}