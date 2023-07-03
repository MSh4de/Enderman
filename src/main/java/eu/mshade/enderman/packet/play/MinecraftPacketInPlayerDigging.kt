package eu.mshade.enderman.packet.play

import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession
import eu.mshade.enderframe.world.Vector
import eu.mshade.enderframe.world.block.BlockFace
import eu.mshade.enderframe.world.block.BlockFace.Companion.fromId
import eu.mshade.enderframe.world.block.DiggingStatus

class MinecraftPacketInPlayerDigging : MinecraftPacketIn {
    var blockPosition: Vector? = null
        private set

    var diggingStatus: DiggingStatus? = null
        private set

    var blockFace: BlockFace? = null
        private set

    private var minecraftSession: MinecraftSession? = null

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
        diggingStatus = DiggingStatus.fromId(minecraftByteBuf.readByte().toInt())
        blockPosition = minecraftByteBuf.readBlockPosition()
        blockFace = fromId(minecraftByteBuf.readByte().toInt())
    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession!!
    }
}
