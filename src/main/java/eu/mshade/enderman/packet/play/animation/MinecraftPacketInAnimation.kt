package eu.mshade.enderman.packet.play.animation

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession

class MinecraftPacketInAnimation : MinecraftPacketIn {

    private lateinit var minecraftSession: MinecraftSession

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession
    }
}