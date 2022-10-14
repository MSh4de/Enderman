package eu.mshade.enderman;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.packetevent.PacketCloseInventoryEvent;
import eu.mshade.enderframe.packetevent.PacketPlayerDiggingEvent;
import eu.mshade.enderframe.packetevent.PacketToggleFlyingEvent;
import eu.mshade.enderframe.protocol.*;
import eu.mshade.enderframe.protocol.packet.*;
import eu.mshade.enderman.metadata.EndermanItemStackManager;
import eu.mshade.enderman.listener.*;
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager;
import eu.mshade.enderman.packet.login.PacketInEncryption;
import eu.mshade.enderman.packet.login.PacketInLogin;
import eu.mshade.enderman.packet.login.PacketOutEncryption;
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.*;
import eu.mshade.enderman.packet.play.inventory.*;
import eu.mshade.enderman.packet.play.scoreboard.PacketOutDisplayScoreboard;
import eu.mshade.enderman.packet.play.scoreboard.PacketOutScoreboardObjective;
import eu.mshade.enderman.packet.play.scoreboard.PacketOutTeams;
import eu.mshade.enderman.packet.play.scoreboard.PacketOutUpdateScoreboard;
import eu.mshade.enderman.wrapper.EndermanInventoryKeyWrapper;
import eu.mshade.enderman.wrapper.EndermanInventorySizeWrapper;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;
import eu.mshade.enderman.wrapper.EndermanParticleWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class EndermanProtocol extends Protocol {

    private final EndermanEntityMetadataManager entityMetadataManager;
    private final EndermanItemStackManager itemStackManager;
    private EndermanMaterialWrapper endermanMaterialWrapper = new EndermanMaterialWrapper();
    private EndermanInventoryKeyWrapper endermanInventoryKeyWrapper = new EndermanInventoryKeyWrapper();
    private EndermanParticleWrapper endermanParticleWrapper = new EndermanParticleWrapper();

    public EndermanProtocol() {

        this.entityMetadataManager = new EndermanEntityMetadataManager();
        this.itemStackManager = new EndermanItemStackManager();

        this.getEventBus().subscribe(PacketInKeepAlive.class, new PacketKeepAliveListener());
        this.getEventBus().subscribe(PacketInLogin.class, new PacketLoginListener());
        this.getEventBus().subscribe(PacketInEncryption.class, new PacketEncryptionListener());
        this.getEventBus().subscribe(PacketInClientSettings.class, new PacketClientSettingsListener());
        this.getEventBus().subscribe(PacketInPlayerPosition.class, new PacketPlayerPositionListener());
        this.getEventBus().subscribe(PacketInPlayerGround.class, new PacketPlayerGroundListener());
        this.getEventBus().subscribe(PacketInPlayerLook.class, new PacketPlayerLookListener());
        this.getEventBus().subscribe(PacketInPlayerPositionAndLook.class, new PacketPlayerPositionAndLookListener());
        this.getEventBus().subscribe(PacketInChatMessage.class, new PacketChatMessageListener());
        this.getEventBus().subscribe(PacketInEntityAction.class, new PacketEntityActionListener());
        this.getEventBus().subscribe(PacketInClickInventory.class, new PacketClickInventoryListener(new EndermanInventorySizeWrapper()));


        this.getEventBus().subscribe(PacketInCloseInventory.class, (event, eventContainer) -> {
            Channel channel = eventContainer.getContainer(Channel.class);
            Player player = ProtocolPipeline.get().getPlayer(channel);
            Inventory inventory = (player.getOpenedInventory() != null ? player.getOpenedInventory() : player.getPlayerInventory());
            EnderFrame.get().getPacketEventBus().publish(new PacketCloseInventoryEvent(inventory), eventContainer);
        });

        this.getEventBus().subscribe(PacketInPlayerAbilities.class, (event, eventContainer) -> {
            EnderFrame.get().getPacketEventBus().publish(new PacketToggleFlyingEvent(event.isAllowFlying()), eventContainer);
        });

        this.getEventBus().subscribe(PacketInBlockPlacement.class, new PacketBlockPlacementListener());
        this.getEventBus().subscribe(PacketInPlayerDigging.class, (event, eventContainer) -> {
           EnderFrame.get().getPacketEventBus().publish(new PacketPlayerDiggingEvent(event.getBlockPosition(), event.getBlockFace(), event.getDiggingStatus()), eventContainer);
        });


        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x00, PacketOutDisconnect.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x01, PacketOutEncryption.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x02, PacketOutLoginSuccess.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x03, PacketOutSetCompression.class);

        this.getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x00, PacketInLogin.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x01, PacketInEncryption.class);

        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x00, PacketInKeepAlive.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x01, PacketInChatMessage.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x03, PacketInPlayerGround.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x04, PacketInPlayerPosition.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x05, PacketInPlayerLook.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x06, PacketInPlayerPositionAndLook.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x07, PacketInPlayerDigging.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x08, PacketInBlockPlacement.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0B, PacketInEntityAction.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x13, PacketInPlayerAbilities.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x15, PacketInClientSettings.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0D, PacketInCloseInventory.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x0E, PacketInClickInventory.class);

        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x00, PacketOutKeepAlive.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x01, PacketOutJoinGame.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x02, PacketOutChatMessage.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x05, PacketOutSpawnPosition.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x07, PacketOutRespawn.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x08, PacketOutPlayerPositionAndLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x0C, PacketOutSpawnPlayer.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x0F,  PacketOutSpawnMob.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x12, PacketOutEntityVelocity.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x13,  PacketOutDestroyEntities.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x14,  PacketOutSpawnEntity.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x15,  PacketOutEntityRelativeMove.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x16,  PacketOutEntityLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x17,  PacketOutEntityLookRelativeMove.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x18,  PacketOutEntityTeleport.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x19, PacketOutEntityHeadLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x1C,  PacketOutEntityMetadata.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x20,  PacketOutEntityProperties.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x21, PacketOutChunkData.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x23, PacketOutBlockChange.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x38, PacketOutPlayerInfo.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x39, PacketOutPlayerAbilities.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x40, PacketOutDisconnect.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x46, PacketOutSetCompression.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x47, PacketOutPlayerList.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2F, PacketOutSetItemStack.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3F, PacketOutPluginMessage.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2B, PacketOutChangeGameState.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2D, PacketOutOpenInventory.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x30, PacketOutInventoryItems.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2E, PacketOutCloseInventory.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3D, PacketOutDisplayScoreboard.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3B, PacketOutScoreboardObjective.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3C, PacketOutUpdateScoreboard.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x3E, PacketOutTeams.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x29, PacketOutSoundEffect.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x45, PacketOutTitle.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x44, PacketOutWorldBorder.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x2A, PacketOutParticle.class);

        this.getEntityRepository().registerEntityTypeId(50, EntityType.CREEPER);
        this.getEntityRepository().registerEntityTypeId(51, EntityType.SKELETON);
        this.getEntityRepository().registerEntityTypeId(52, EntityType.SPIDER);
        this.getEntityRepository().registerEntityTypeId(53, EntityType.GIANT);
        this.getEntityRepository().registerEntityTypeId(54, EntityType.ZOMBIE);
        this.getEntityRepository().registerEntityTypeId(55, EntityType.SLIME);
        this.getEntityRepository().registerEntityTypeId(56, EntityType.GHAST);
        this.getEntityRepository().registerEntityTypeId(57, EntityType.PIG_ZOMBIE);
        this.getEntityRepository().registerEntityTypeId(58, EntityType.ENDERMAN);
        this.getEntityRepository().registerEntityTypeId(59, EntityType.CAVE_SPIDER);
        this.getEntityRepository().registerEntityTypeId(60, EntityType.SILVERFISH);
        this.getEntityRepository().registerEntityTypeId(61, EntityType.BLAZE);
        this.getEntityRepository().registerEntityTypeId(62, EntityType.MAGMA_CUBE);
        this.getEntityRepository().registerEntityTypeId(63, EntityType.ENDER_DRAGON);
        this.getEntityRepository().registerEntityTypeId(64, EntityType.WITHER);
        this.getEntityRepository().registerEntityTypeId(65, EntityType.BAT);
        this.getEntityRepository().registerEntityTypeId(66, EntityType.WITCH);
        this.getEntityRepository().registerEntityTypeId(90, EntityType.PIG);
        this.getEntityRepository().registerEntityTypeId(91, EntityType.SHEEP);
        this.getEntityRepository().registerEntityTypeId(92, EntityType.COW);
        this.getEntityRepository().registerEntityTypeId(93, EntityType.CHICKEN);
        this.getEntityRepository().registerEntityTypeId(94, EntityType.SQUID);
        this.getEntityRepository().registerEntityTypeId(95, EntityType.WOLF);
        this.getEntityRepository().registerEntityTypeId(96, EntityType.MOOSHROOM);
        this.getEntityRepository().registerEntityTypeId(97, EntityType.SNOWMAN);
        this.getEntityRepository().registerEntityTypeId(98, EntityType.OCELOT);
        this.getEntityRepository().registerEntityTypeId(99, EntityType.IRON_GOLEM);
        this.getEntityRepository().registerEntityTypeId(100, EntityType.HORSE);
        this.getEntityRepository().registerEntityTypeId(120, EntityType.VILLAGER);
    }


    @Override
    public ProtocolBuffer getProtocolBuffer(ByteBuf byteBuf) {
        return new EndermanProtocolBuffer(this.entityMetadataManager, this.itemStackManager, endermanMaterialWrapper, byteBuf);
    }

    @Override
    public SessionWrapper getSessionWrapper(Channel channel) {
        return new EndermanSessionWrapper(channel, this.getEntityRepository(), endermanMaterialWrapper, endermanInventoryKeyWrapper, endermanParticleWrapper);
    }

    @Override
    public MinecraftProtocolVersion getMinecraftProtocolVersion() {
        return MinecraftProtocolVersion.V1_8;
    }

}
