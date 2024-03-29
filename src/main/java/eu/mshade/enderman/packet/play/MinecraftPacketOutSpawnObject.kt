package eu.mshade.enderman.packet.play

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut


class MinecraftPacketOutSpawnObject(private val entityId: Int, val entity: Entity, private val data: Int): MinecraftPacketOut {

    override fun serialize(protocolBuffer: MinecraftByteBuf) {
        protocolBuffer.writeVarInt(entity.entityId)
        protocolBuffer.writeByte(entityId)
        protocolBuffer.writeInt(entity.location.blockX * 32)
        protocolBuffer.writeInt(entity.location.blockY * 32)
        protocolBuffer.writeInt(entity.location.blockZ * 32)
        protocolBuffer.writeByte((entity.location.yaw * 256 / 360).toInt())
        protocolBuffer.writeByte((entity.location.pitch * 256 / 360).toInt())

        protocolBuffer.writeInt(data)

        val velocity = entity.velocity

        if (data > 0.0) {
            protocolBuffer.writeShort((velocity.x * 8000).toInt())
            protocolBuffer.writeShort((velocity.y * 8000).toInt())
            protocolBuffer.writeShort((velocity.z * 8000).toInt())
        }
    }
}