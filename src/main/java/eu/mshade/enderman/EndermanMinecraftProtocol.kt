package eu.mshade.enderman

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.item.MaterialCategory
import eu.mshade.enderframe.packetevent.MinecraftPacketCloseInventoryEvent
import eu.mshade.enderframe.packetevent.MinecraftPacketPlayerDiggingEvent
import eu.mshade.enderframe.packetevent.MinecraftPacketToggleFlyingEvent
import eu.mshade.enderframe.protocol.*
import eu.mshade.enderframe.protocol.packet.*
import eu.mshade.enderframe.wrapper.ContextWrapper
import eu.mshade.enderman.listener.*
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager
import eu.mshade.enderman.metadata.EndermanItemStackManager
import eu.mshade.enderman.packet.login.MinecraftPacketInEncryption
import eu.mshade.enderman.packet.login.MinecraftPacketInLogin
import eu.mshade.enderman.packet.login.MinecraftPacketOutEncryption
import eu.mshade.enderman.packet.login.MinecraftPacketOutLoginSuccess
import eu.mshade.enderman.packet.play.*
import eu.mshade.enderman.packet.play.inventory.*
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerGround
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerLook
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPosition
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPositionAndLook
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutDisplayScoreboard
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutScoreboardObjective
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutTeams
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutUpdateScoreboard
import eu.mshade.enderman.wrapper.*
import eu.mshade.mwork.event.EventListener
import io.netty.buffer.ByteBuf
import io.netty.channel.Channel

class EndermanMinecraftProtocol : MinecraftProtocol() {
    private val entityMetadataManager: EndermanEntityMetadataManager
    private val itemStackManager: EndermanItemStackManager

    init {
        val endermanMaterialKeyWrapper = EndermanMaterialKeyWrapper()
        wrapperRepository.register(ContextWrapper.MATERIAL_KEY, endermanMaterialKeyWrapper)
        wrapperRepository.register(EndermanContextWrapper.INVENTORY_KEY, EndermanInventoryKeyWrapper())
        wrapperRepository.register(EndermanContextWrapper.INVENTORY_SIZE, EndermanInventorySizeWrapper())
        wrapperRepository.register(EndermanContextWrapper.ATTRIBUTE_KEY, EndermanAttributeKeyWrapper())
        wrapperRepository.register(EndermanContextWrapper.ENCHANTMENT_TYPE, EndermanEnchantmentTypeWrapper())
        wrapperRepository.register(EndermanContextWrapper.EQUIPMENT_SLOT, EndermanEquipmentSlotWrapper())
        wrapperRepository.register(EndermanContextWrapper.ENTITY_TYPE, EndermanEntityTypeWrapper())
        wrapperRepository.register(EndermanContextWrapper.NAMESPACED_KEY, EndermanNamespacedKeyWrapper())
        wrapperRepository.register(EndermanContextWrapper.PARTICLE_TYPE, EndermanParticleWrapper())

        entityMetadataManager = EndermanEntityMetadataManager()
        itemStackManager = EndermanItemStackManager(getWrapperRepository())

        getEventBus().subscribe(MinecraftPacketInKeepAlive::class.java, PacketKeepAliveListener())
        getEventBus().subscribe(MinecraftPacketInLogin::class.java, PacketLoginListener())
        getEventBus().subscribe(MinecraftPacketInEncryption::class.java, PacketEncryptionListener())
        getEventBus().subscribe(MinecraftPacketInClientSettings::class.java, PacketClientSettingsListener())
        getEventBus().subscribe(MinecraftPacketInPlayerPosition::class.java, PacketPlayerPositionListener())
        getEventBus().subscribe(MinecraftPacketInPlayerGround::class.java, PacketPlayerGroundListener())
        getEventBus().subscribe(MinecraftPacketInPlayerLook::class.java, PacketPlayerLookListener())
        getEventBus().subscribe(MinecraftPacketInPlayerPositionAndLook::class.java, PacketPlayerPositionAndLookListener())
        getEventBus().subscribe(MinecraftPacketInChatMessage::class.java, PacketChatMessageListener())
        getEventBus().subscribe(MinecraftPacketInEntityAction::class.java, PacketEntityActionListener())
        getEventBus().subscribe(
            MinecraftPacketInClickInventory::class.java,
            PacketClickInventoryListener(EndermanInventorySizeWrapper())
        )


        getEventBus().subscribe(
            MinecraftPacketInCloseInventory::class.java,
            object : EventListener<MinecraftPacketInCloseInventory> {
                override fun onEvent(event: MinecraftPacketInCloseInventory) {
                    val player = event.sessionWrapper.player
                    val inventory = if (player.openedInventory != null) player.openedInventory else player.inventory
                    EnderFrame.get().packetEventBus.publish(
                        MinecraftPacketCloseInventoryEvent(
                            player,
                            inventory
                        )
                    )
                }
            })
        getEventBus().subscribe(
            MinecraftPacketInPlayerAbilities::class.java,
            object : EventListener<MinecraftPacketInPlayerAbilities> {
                override fun onEvent(event: MinecraftPacketInPlayerAbilities) {
                    val player = event.sessionWrapper.player
                    EnderFrame.get().packetEventBus.publish(
                        MinecraftPacketToggleFlyingEvent(
                            player,
                            event.isAllowFlying
                        )
                    )
                }
            })

        getEventBus().subscribe(MinecraftPacketInBlockPlacement::class.java, PacketBlockPlacementListener())

        getEventBus().subscribe(
            MinecraftPacketInPlayerDigging::class.java,
            object : EventListener<MinecraftPacketInPlayerDigging> {
                override fun onEvent(event: MinecraftPacketInPlayerDigging) {
                    val player = event.sessionWrapper.player
                    EnderFrame.get().packetEventBus.publish(
                        MinecraftPacketPlayerDiggingEvent(
                            player,
                            event.blockPosition,
                            event.blockFace,
                            event.diggingStatus
                        )
                    )
                }
            })

        blockTransformerRepository.registerDefaultTransformer(CommonBlockTransformer())

        blockTransformerRepository.registerMaterialWrapper(endermanMaterialKeyWrapper)
        blockTransformerRepository.register(MaterialCategory.LOG, LogBlockTransFormer())
        blockTransformerRepository.register(MaterialCategory.STAIRS, StairsBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.BUTTON, ButtonBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.LEVER, LeverBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.SLAB, SlabBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.LEAVES, LeavesBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.DOUBLE_SLAB, DoubleSlabBlockTransformer())
        blockTransformerRepository.register(MaterialCategory.VINE, VineBlockTransformer())


        getProtocolRegistry().registerOut(MinecraftProtocolStatus.LOGIN, 0x00, MinecraftPacketOutDisconnect::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.LOGIN, 0x01, MinecraftPacketOutEncryption::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.LOGIN, 0x02, MinecraftPacketOutLoginSuccess::class.java)

        getProtocolRegistry().registerOut(MinecraftProtocolStatus.LOGIN, 0x03, MinecraftPacketOutSetCompression::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.LOGIN, 0x00, MinecraftPacketInLogin::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.LOGIN, 0x01, MinecraftPacketInEncryption::class.java)

        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x00, MinecraftPacketInKeepAlive::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x01, MinecraftPacketInChatMessage::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x03, MinecraftPacketInPlayerGround::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x04, MinecraftPacketInPlayerPosition::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x05, MinecraftPacketInPlayerLook::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x06, MinecraftPacketInPlayerPositionAndLook::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x07, MinecraftPacketInPlayerDigging::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x08, MinecraftPacketInBlockPlacement::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x0B, MinecraftPacketInEntityAction::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x13, MinecraftPacketInPlayerAbilities::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x15, MinecraftPacketInClientSettings::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x0D, MinecraftPacketInCloseInventory::class.java)
        getProtocolRegistry().registerIn(MinecraftProtocolStatus.PLAY, 0x0E, MinecraftPacketInClickInventory::class.java)

        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x00, MinecraftPacketOutKeepAlive::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x01, MinecraftPacketOutJoinGame::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x02, MinecraftPacketOutChatMessage::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x05, MinecraftPacketOutSpawnPosition::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x07, MinecraftPacketOutRespawn::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x08, MinecraftPacketOutPlayerPositionAndLook::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x0C, MinecraftPacketOutSpawnPlayer::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x0F, MinecraftPacketOutSpawnMob::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x12, MinecraftPacketOutEntityVelocity::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x13, MinecraftPacketOutDestroyEntities::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x14, MinecraftPacketOutSpawnEntity::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x15, MinecraftPacketOutEntityRelativeMove::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x16, MinecraftPacketOutEntityLook::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x17, MinecraftPacketOutEntityLookRelativeMove::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x18, MinecraftPacketOutEntityTeleport::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x19, MinecraftPacketOutEntityHeadLook::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x1C, MinecraftPacketOutEntityMetadata::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x20, MinecraftPacketOutEntityProperties::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x21, MinecraftPacketOutChunkData::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x23, MinecraftPacketOutBlockChange::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x38, MinecraftPacketOutPlayerInfo::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x39, MinecraftPacketOutPlayerAbilities::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x40, MinecraftPacketOutDisconnect::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x46, MinecraftPacketOutSetCompression::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x47, MinecraftPacketOutPlayerList::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x2F, MinecraftPacketOutSetItemStack::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x3F, MinecraftPacketOutPluginMessage::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x2B, MinecraftPacketOutChangeGameState::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x2D, MinecraftPacketOutOpenInventory::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x30, MinecraftPacketOutInventoryItems::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x2E, MinecraftPacketOutCloseInventory::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x3D, MinecraftPacketOutDisplayScoreboard::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x3B, MinecraftPacketOutScoreboardObjective::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x3C, MinecraftPacketOutUpdateScoreboard::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x3E, MinecraftPacketOutTeams::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x29, MinecraftPacketOutSoundEffect::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x45, MinecraftPacketOutTitle::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x44, MinecraftPacketOutWorldBorder::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x33, MinecraftPacketOutUpdateSign::class.java)
        getProtocolRegistry().registerOut(MinecraftProtocolStatus.PLAY, 0x2A, MinecraftPacketOutParticle::class.java)
    }

    override fun getProtocolBuffer(byteBuf: ByteBuf): MinecraftByteBuf {
        return EndermanMinecraftByteBuf(
            entityMetadataManager,
            itemStackManager,
            getWrapperRepository(),
            byteBuf
        )
    }

    override fun getSessionWrapper(channel: Channel): MinecraftSession {
        return EndermanMinecraftSession(
            channel,
            getWrapperRepository(),
            getBlockTransformerRepository()
        )
    }

    override fun getMinecraftProtocolVersion(): MinecraftProtocolVersion {
        return MinecraftProtocolVersion.V1_8
    }
}