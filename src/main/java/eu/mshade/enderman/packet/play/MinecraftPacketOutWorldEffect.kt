package eu.mshade.enderman.packet.play

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut
import eu.mshade.enderframe.world.Vector
import eu.mshade.enderframe.world.effect.WorldEffectType

class MinecraftPacketOutWorldEffect(
    private val worldEffectId: Int,
    private val location: Vector,
    private val id: Int,
    private val relativeVolume: Boolean
) : MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeInt(worldEffectId)
        minecraftByteBuf.writeBlockPosition(location)
        minecraftByteBuf.writeInt(id)
        minecraftByteBuf.writeBoolean(relativeVolume)
    }
}