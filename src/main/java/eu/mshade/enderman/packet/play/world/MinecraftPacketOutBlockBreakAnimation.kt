package eu.mshade.enderman.packet.play.world

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftPacketOut
import eu.mshade.enderframe.protocol.MinecraftSession
import eu.mshade.enderframe.world.Vector

class MinecraftPacketOutBlockBreakAnimation(val entityId: Int, val position: Vector, val destroyStage: Int) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entityId)
        minecraftByteBuf.writeBlockPosition(position)
        minecraftByteBuf.writeByte(destroyStage)
    }

}