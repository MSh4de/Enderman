package eu.mshade.enderman.metadata

import eu.mshade.enderframe.entity.EntityType
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataType
import eu.mshade.enderframe.metadata.entity.EntityMetadataManager
import eu.mshade.enderframe.protocol.ProtocolBuffer
import eu.mshade.enderframe.world.Rotation
import eu.mshade.enderframe.world.Vector
import eu.mshade.enderman.metadata.entity.ArmorStandMetadataBucket
import eu.mshade.enderman.metadata.entity.PlayerMetadataBucket
import eu.mshade.enderman.metadata.entity.ZombieMetadataBucket

class EndermanEntityMetadataManager: EntityMetadataManager() {

    init {
        registerMetadataIndex(0, MetadataType.BYTE) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeByte((metadata.get() as Byte).toInt())
        }

        registerMetadataIndex(1, MetadataType.SHORT) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeShort((metadata.get() as Short).toInt())
        }

        registerMetadataIndex(2, MetadataType.INTEGER) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeInt(metadata.get() as Int)
        }

        registerMetadataIndex(3, MetadataType.FLOAT) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeFloat(metadata.get() as Float)
        }

        registerMetadataIndex(4, MetadataType.STRING) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeString(metadata.get() as String)
        }

        registerMetadataIndex(5, MetadataType.SLOT) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            protocolBuffer.writeItemStack(metadata.get() as ItemStack)
        }

        registerMetadataIndex(6, MetadataType.BLOCK_POSITION) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            val vector = metadata.get() as Vector
            protocolBuffer.writeInt(vector.blockX)
            protocolBuffer.writeInt(vector.blockY)
            protocolBuffer.writeInt(vector.blockZ)
        }

        registerMetadataIndex(7, MetadataType.ROTATION) { protocolBuffer: ProtocolBuffer, metadata: Metadata<*> ->
            val rotation = metadata.get() as Rotation
            protocolBuffer.writeFloat(rotation.yaw)
            protocolBuffer.writeFloat(rotation.pitch)
            protocolBuffer.writeFloat(rotation.roll)
        }

        registerEntityMetadataBucket(EntityType.PLAYER, PlayerMetadataBucket())
        registerEntityMetadataBucket(EntityType.ARMOR_STAND, ArmorStandMetadataBucket())
        registerEntityMetadataBucket(EntityType.ZOMBIE, ZombieMetadataBucket())
    }
}