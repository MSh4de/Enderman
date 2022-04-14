package eu.mshade.enderman;

import eu.mshade.enderframe.EnderFrameSession;
import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.PlayerInfoBuilder;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.metadata.*;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.ProtocolStatus;
import eu.mshade.enderframe.protocol.packet.PacketOutPlayerAbilities;
import eu.mshade.enderframe.protocol.packet.PacketOutSetCompression;
import eu.mshade.enderframe.world.*;
import eu.mshade.enderman.itemstack.EndermanItemStackManager;
import eu.mshade.enderman.metadata.EndermanEntityMetadataManager;
import eu.mshade.enderman.packet.login.PacketOutEncryption;
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.PacketOutChunkData;
import eu.mshade.enderman.packet.play.PacketOutJoinGame;
import eu.mshade.enderman.packet.play.PacketOutKeepAlive;
import eu.mshade.enderman.packet.play.PacketOutPlayerPositionAndLook;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class EndermanProtocolBuffer implements ProtocolBuffer {

    private final EndermanEntityMetadataManager entityMetadataManager = new EndermanEntityMetadataManager(this);
    private final EndermanItemStackManager itemStackManager = new EndermanItemStackManager();


    @Override
    public void writeItemStack(ByteBuf byteBuf, ItemStack itemStack) {

    }

    @Override
    public ItemStack readItemStack(ByteBuf byteBuf) {
        return null;
    }

    @Override
    public void writeEntityMetadata(ByteBuf byteBuf, Entity entity, EntityMetadataType... entityMetadataTypes) {
        for (EntityMetadataType entityMetadataType : entityMetadataTypes) {
            EntityMetadataBucket entityMetadataBucket = entityMetadataManager.getEntityMetadataBucket(entity);
            EntityMetadataWrapper<Entity> entityMetadataBuffer = entityMetadataBucket.getEntityMetadataBuffer(entityMetadataType);
            Metadata<?> metadata = entityMetadataBuffer.wrap(entity);
            int i = (entityMetadataManager.getMetadataIndex(metadata.getMetadataType())) << 5 | entityMetadataBucket.getIndexEntityMetadata(entityMetadataType);
            byteBuf.writeByte(i);
            MetadataBuffer<Metadata<?>> metadataBuffer = (MetadataBuffer<Metadata<?>>) entityMetadataManager.getMetadataBuffer(metadata.getMetadataType());
            metadataBuffer.write(byteBuf, metadata);
        }
    }

    @Override
    public void sendHeadAndFooter(Player player, String header, String footer) {
    }

    @Override
    public void sendAbilities(Player player) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutPlayerAbilities(player.isInvulnerable(), player.isFlying(), player.isAllowFlying(), player.isInstantBreak(), player.getFlyingSpeed(), player.getWalkSpeed()));
    }

    @Override
    public void sendKeepAlive(Player player, int threshold) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutKeepAlive(threshold));
    }

    @Override
    public void sendEncryption(Player player, PublicKey publicKey) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        String sessionId = enderFrameSession.getSessionId();
        byte[] verifyToken = enderFrameSession.getVerifyToken();
        enderFrameSession.sendPacket(new PacketOutEncryption(sessionId, publicKey, verifyToken));
    }

    @Override
    public void sendCompression(Player player, int threshold) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutSetCompression(threshold));
        enderFrameSession.enableCompression(threshold);
    }

    @Override
    public void sendLoginSuccess(Player player) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutLoginSuccess(player.getGameProfile()));
        enderFrameSession.toggleProtocolStatus(ProtocolStatus.PLAY);
    }

    @Override
    public void sendJoinGame(Player player, World world, boolean reducedDebugInfo) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        final int entityId = player.getEntityId();
        final GameMode gameMode = player.getGameMode();
        final WorldLevel worldLevel = world.getWorldLevel();
        final Dimension dimension = worldLevel.getDimension();
        final Difficulty difficulty = worldLevel.getDifficulty();
        final String name = worldLevel.getName();
        enderFrameSession.sendPacket(new PacketOutJoinGame(entityId, gameMode, dimension, difficulty, -1, name, reducedDebugInfo));
    }

    @Override
    public void sendPlayerInfo(Player player, PlayerInfoBuilder playerInfoBuilder) {

    }

    @Override
    public void sendMessage(Player player, TextComponent textComponent, TextPosition textPosition) {

    }

    @Override
    public void sendMessage(Player player, TextComponent textComponent) {

    }

    @Override
    public void sendMessage(Player player, String message) {

    }


    @Override
    public void disconnect(EnderFrameSession enderFrameSession, String message) {

    }

    @Override
    public void teleport(Player player, Location location) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutPlayerPositionAndLook(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }

    @Override
    public void sendUpdateLocation(Player player, Entity entity) {

    }

    @Override
    public void sendTeleport(Player player, Entity entity) {

    }

    @Override
    public void sendMove(Player player, Entity entity) {

    }

    @Override
    public void sendMoveAndLook(Player player, Entity entity) {

    }

    @Override
    public void sendLook(Player player, Entity entity) {

    }

    @Override
    public void sendHeadLook(Player player, Entity entity) {

    }

    @Override
    public void sendEntity(Player player, Entity... entities) {

    }

    @Override
    public void removeEntity(Player player, Entity... entities) {

    }

    @Override
    public void sendMetadata(Player player, Entity entity, EntityMetadataType... entityMetadataTypes) {

    }

    @Override
    public void sendChunk(Player player, Chunk chunk) {
        List<Section> sections = new ArrayList<>();
        for (Section section : chunk.getSectionBuffers()) {
            if (section != null && section.getRealBlock() != 0) {
                sections.add(section);
            }
        }
        int capacity = sections.size() * (4096 * 4) + 256;

        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        for (Section section : sections) {
            for (int i = 0; i < 4096; i++) {
                byteBuffer.put((byte) (section.getBlocks()[i] << 4 | section.getData().get(i)));
                byteBuffer.put((byte) (section.getBlocks()[i] >> 4));
            }
        }

        for (Section section : sections) byteBuffer.put(section.getBlockLight().getRawData());
        for (Section section : sections) byteBuffer.put(section.getSkyLight().getRawData());


        byteBuffer.put(chunk.getBiomes());
        player.getEnderFrameSessionHandler().sendPacket(new PacketOutChunkData(chunk.getX(), chunk.getZ(), true, chunk.getBitMask(), byteBuffer.array()));
    }

    @Override
    public void sendSection(Player player, Section section) {

    }

    @Override
    public void sendUnloadChunk(Player player, Chunk chunk) {
        EnderFrameSession enderFrameSession = player.getEnderFrameSessionHandler();
        enderFrameSession.sendPacket(new PacketOutChunkData(chunk.getX(), chunk.getZ(), true, 0, new byte[0]));
    }
}
