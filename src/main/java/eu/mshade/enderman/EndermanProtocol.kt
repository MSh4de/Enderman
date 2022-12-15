package eu.mshade.enderman

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.item.MaterialCategory
import eu.mshade.enderframe.packetevent.PacketCloseInventoryEvent
import eu.mshade.enderframe.packetevent.PacketPlayerDiggingEvent
import eu.mshade.enderframe.packetevent.PacketToggleFlyingEvent
import eu.mshade.enderframe.protocol.*
import eu.mshade.enderframe.protocol.packet.*
import eu.mshade.enderframe.wrapper.ContextWrapper
import eu.mshade.enderman.listener.*
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager
import eu.mshade.enderman.metadata.EndermanItemStackManager
import eu.mshade.enderman.packet.login.PacketInEncryption
import eu.mshade.enderman.packet.login.PacketInLogin
import eu.mshade.enderman.packet.login.PacketOutEncryption
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess
import eu.mshade.enderman.packet.play.*
import eu.mshade.enderman.packet.play.inventory.*
import eu.mshade.enderman.packet.play.move.PacketInPlayerGround
import eu.mshade.enderman.packet.play.move.PacketInPlayerLook
import eu.mshade.enderman.packet.play.move.PacketInPlayerPosition
import eu.mshade.enderman.packet.play.move.PacketInPlayerPositionAndLook
import eu.mshade.enderman.packet.play.scoreboard.PacketOutDisplayScoreboard
import eu.mshade.enderman.packet.play.scoreboard.PacketOutScoreboardObjective
import eu.mshade.enderman.packet.play.scoreboard.PacketOutTeams
import eu.mshade.enderman.packet.play.scoreboard.PacketOutUpdateScoreboard
import eu.mshade.enderman.wrapper.*
import eu.mshade.mwork.event.EventListener
import io.netty.buffer.ByteBuf
import io.netty.channel.Channel

class EndermanProtocol : Protocol() {
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

        getEventBus().subscribe(PacketInKeepAlive::class.java, PacketKeepAliveListener())
        getEventBus().subscribe(PacketInLogin::class.java, PacketLoginListener())
        getEventBus().subscribe(PacketInEncryption::class.java, PacketEncryptionListener())
        getEventBus().subscribe(PacketInClientSettings::class.java, PacketClientSettingsListener())
        getEventBus().subscribe(PacketInPlayerPosition::class.java, PacketPlayerPositionListener())
        getEventBus().subscribe(PacketInPlayerGround::class.java, PacketPlayerGroundListener())
        getEventBus().subscribe(PacketInPlayerLook::class.java, PacketPlayerLookListener())
        getEventBus().subscribe(PacketInPlayerPositionAndLook::class.java, PacketPlayerPositionAndLookListener())
        getEventBus().subscribe(PacketInChatMessage::class.java, PacketChatMessageListener())
        getEventBus().subscribe(PacketInEntityAction::class.java, PacketEntityActionListener())
        getEventBus().subscribe(
            PacketInClickInventory::class.java,
            PacketClickInventoryListener(EndermanInventorySizeWrapper())
        )


        getEventBus().subscribe(
            PacketInCloseInventory::class.java,
            object : EventListener<PacketInCloseInventory> {
                override fun onEvent(event: PacketInCloseInventory) {
                    val player = event.sessionWrapper.player
                    val inventory = if (player.openedInventory != null) player.openedInventory else player.inventory
                    EnderFrame.get().packetEventBus.publish(PacketCloseInventoryEvent(player, inventory))
                }
            })
        getEventBus().subscribe(
            PacketInPlayerAbilities::class.java,
            object : EventListener<PacketInPlayerAbilities> {
                override fun onEvent(event: PacketInPlayerAbilities) {
                    val player = event.sessionWrapper.player
                    EnderFrame.get().packetEventBus.publish(PacketToggleFlyingEvent(player, event.isAllowFlying))
                }
            })

        getEventBus().subscribe(PacketInBlockPlacement::class.java, PacketBlockPlacementListener())

        getEventBus().subscribe(
            PacketInPlayerDigging::class.java,
            object : EventListener<PacketInPlayerDigging> {
                override fun onEvent(event: PacketInPlayerDigging) {
                    val player = event.sessionWrapper.player
                    EnderFrame.get().packetEventBus.publish(PacketPlayerDiggingEvent(player, event.blockPosition, event.blockFace, event.diggingStatus)
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


        getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x00, PacketOutDisconnect::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x01, PacketOutEncryption::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x02, PacketOutLoginSuccess::class.java)

        getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x03, PacketOutSetCompression::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x00, PacketInLogin::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x01, PacketInEncryption::class.java)

        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x00, PacketInKeepAlive::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x01, PacketInChatMessage::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x03, PacketInPlayerGround::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x04, PacketInPlayerPosition::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x05, PacketInPlayerLook::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x06, PacketInPlayerPositionAndLook::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x07, PacketInPlayerDigging::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x08, PacketInBlockPlacement::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0B, PacketInEntityAction::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x13, PacketInPlayerAbilities::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x15, PacketInClientSettings::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0D, PacketInCloseInventory::class.java)
        getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0E, PacketInClickInventory::class.java)

        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x00, PacketOutKeepAlive::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x01, PacketOutJoinGame::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x02, PacketOutChatMessage::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x05, PacketOutSpawnPosition::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x07, PacketOutRespawn::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x08, PacketOutPlayerPositionAndLook::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x0C, PacketOutSpawnPlayer::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x0F, PacketOutSpawnMob::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x12, PacketOutEntityVelocity::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x13, PacketOutDestroyEntities::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x14, PacketOutSpawnEntity::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x15, PacketOutEntityRelativeMove::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x16, PacketOutEntityLook::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x17, PacketOutEntityLookRelativeMove::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x18, PacketOutEntityTeleport::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x19, PacketOutEntityHeadLook::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x1C, PacketOutEntityMetadata::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x20, PacketOutEntityProperties::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x21, PacketOutChunkData::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x23, PacketOutBlockChange::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x38, PacketOutPlayerInfo::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x39, PacketOutPlayerAbilities::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x40, PacketOutDisconnect::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x46, PacketOutSetCompression::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x47, PacketOutPlayerList::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2F, PacketOutSetItemStack::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3F, PacketOutPluginMessage::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2B, PacketOutChangeGameState::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2D, PacketOutOpenInventory::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x30, PacketOutInventoryItems::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2E, PacketOutCloseInventory::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3D, PacketOutDisplayScoreboard::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3B, PacketOutScoreboardObjective::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3C, PacketOutUpdateScoreboard::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3E, PacketOutTeams::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x29, PacketOutSoundEffect::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x45, PacketOutTitle::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x44, PacketOutWorldBorder::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x33, PacketOutUpdateSign::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2A, PacketOutParticle::class.java)
        getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x31, PacketOutWindowProperty::class.java)
    }

    override fun getProtocolBuffer(byteBuf: ByteBuf): ProtocolBuffer {
        return EndermanProtocolBuffer(entityMetadataManager, itemStackManager, getWrapperRepository(), byteBuf)
    }

    override fun getSessionWrapper(channel: Channel): SessionWrapper {
        return EndermanSessionWrapper(channel, getWrapperRepository(), getBlockTransformerRepository())
    }

    override fun getMinecraftProtocolVersion(): MinecraftProtocolVersion {
        return MinecraftProtocolVersion.V1_8
    }
}