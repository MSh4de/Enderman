package eu.mshade.enderman.packet.play.chat

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession

class MinecraftPacketInTabComplete : MinecraftPacketIn {

    private lateinit var minecraftSession: MinecraftSession
    lateinit var text: String

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
        text = minecraftByteBuf.readString()
    }

    override fun getMinecraftSession(): MinecraftSession = minecraftSession

    override fun toString(): String {
        return "PacketInTabComplete(text='$text')"
    }

}