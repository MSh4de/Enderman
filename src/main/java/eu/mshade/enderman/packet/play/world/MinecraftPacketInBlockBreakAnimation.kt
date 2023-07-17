package eu.mshade.enderman.packet.play.world

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession
import eu.mshade.enderframe.world.Vector

class MinecraftPacketInBlockBreakAnimation: MinecraftPacketIn {

    lateinit var minecraftSession: MinecraftSession
    var entityId: Int = 0
    var blockPosition: Vector = Vector.ZERO
    var destroyStage: Int = 0

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession

        entityId = minecraftByteBuf.readVarInt()
        blockPosition = minecraftByteBuf.readBlockPosition()
        destroyStage = minecraftByteBuf.readByte().toInt()
    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession
    }

}