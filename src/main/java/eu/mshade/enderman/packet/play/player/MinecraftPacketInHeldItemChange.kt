package eu.mshade.enderman.packet.play.player

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession

class MinecraftPacketInHeldItemChange: MinecraftPacketIn {

    private lateinit var minecraftSession: MinecraftSession
    private var slot: Int = 0

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
        this.slot = minecraftByteBuf.readShort().toInt()

    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession
    }

    fun getSlot(): Int {
        return slot
    }
}