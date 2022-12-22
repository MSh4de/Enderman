package eu.mshade.enderman.metadata

import eu.mshade.enderframe.entity.EntityType
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataType
import eu.mshade.enderframe.metadata.entity.EntityMetadataManager
import eu.mshade.enderframe.protocol.ProtocolBuffer
import eu.mshade.enderframe.world.Rotation
import eu.mshade.enderframe.world.Vector
import eu.mshade.enderman.metadata.entity.*

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

        //Hostile
        registerEntityMetadataBucket(EntityType.BLAZE, BlazeMetadataBucket())
        registerEntityMetadataBucket(EntityType.CREEPER, CreeperMetadataBucket())
        registerEntityMetadataBucket(EntityType.GHAST, GhastMetadataBucket())
        registerEntityMetadataBucket(EntityType.GUARDIAN, GuardianMetadataBucket())
        registerEntityMetadataBucket(EntityType.MAGMA_CUBE, MagmaCubeMetadataBucket())
        registerEntityMetadataBucket(EntityType.SKELETON, SkeletonMetadataBucket())
        registerEntityMetadataBucket(EntityType.SLIME, SlimeMetadataBucket())
        registerEntityMetadataBucket(EntityType.ZOMBIE, ZombieMetadataBucket())
        registerEntityMetadataBucket(EntityType.WITCH, WitchMetadataBucket())

        //Passive
        registerEntityMetadataBucket(EntityType.BAT, BatMetadataBucket())
        registerEntityMetadataBucket(EntityType.CHICKEN, ChickenMetadataBucket())
        registerEntityMetadataBucket(EntityType.HORSE, HorseMetadataBucket())
        registerEntityMetadataBucket(EntityType.OCELOT, OcelotMetadataBucket())
        registerEntityMetadataBucket(EntityType.PIG, PigMetadataBucket())
        registerEntityMetadataBucket(EntityType.RABBIT, RabbitMetadataBucket())
        registerEntityMetadataBucket(EntityType.SHEEP, SheepMetadataBucket())
        registerEntityMetadataBucket(EntityType.VILLAGER, VillagerMetadataBucket())

        //Neutral
        registerEntityMetadataBucket(EntityType.CAVE_SPIDER, CaveSpiderMetadataBucket())
        registerEntityMetadataBucket(EntityType.ENDERMAN, EndermanMetadataBucket())
        registerEntityMetadataBucket(EntityType.CAVE_SPIDER, IronGolemMetadataBucket())
        registerEntityMetadataBucket(EntityType.SPIDER, SpiderMetadataBucket())
        registerEntityMetadataBucket(EntityType.WOLF, WolfMetadataBucket())

        //Boss
        registerEntityMetadataBucket(EntityType.WITHER, WitherMetadataBucket())

        //TileEntity
        registerEntityMetadataBucket(EntityType.ARMOR_STAND, ArmorStandMetadataBucket())
        registerEntityMetadataBucket(EntityType.BOAT, BoatMetadataBucket())
        registerEntityMetadataBucket(EntityType.MINECART, MinecartMetadataBucket())
        registerEntityMetadataBucket(EntityType.MINECART_FURNACE, FurnaceMinecartMetadataBucket())
        registerEntityMetadataBucket(EntityType.DROPPED_ITEM, ItemMetadataBucket())
        registerEntityMetadataBucket(EntityType.ARROW, ArrowMetadataBucket())
        registerEntityMetadataBucket(EntityType.FIREWORK, FireworkMetadataBucket())
        registerEntityMetadataBucket(EntityType.ITEM_FRAME, ItemFrameMetadataBucket())
        registerEntityMetadataBucket(EntityType.ENDER_CRYSTAL, EnderCrystalMetadataBucket())
    }
}