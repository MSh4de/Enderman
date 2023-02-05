package eu.mshade.enderman.packet.play.player

import eu.mshade.enderframe.ClientStatus
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession

class MinecraftPacketInClientStatus: MinecraftPacketIn {

    private lateinit var minecraftSession: MinecraftSession
    lateinit var clientStatus: ClientStatus

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
        clientStatus = ClientStatus.fromId(minecraftByteBuf.readVarInt())
    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession
    }



}