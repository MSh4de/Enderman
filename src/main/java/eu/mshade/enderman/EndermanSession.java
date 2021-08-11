package eu.mshade.enderman;

import eu.mshade.enderframe.EnderFrameSession;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.PlayerInfoBuilder;
import eu.mshade.enderframe.entity.*;
import eu.mshade.enderframe.event.entity.PacketMoveType;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.mojang.GameProfile;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.ProtocolStatus;
import eu.mshade.enderframe.protocol.ProtocolVersion;
import eu.mshade.enderframe.protocol.packet.PacketOutDisconnect;
import eu.mshade.enderframe.protocol.packet.PacketOutPlayerInfo;
import eu.mshade.enderframe.protocol.packet.PacketOutSetCompression;
import eu.mshade.enderframe.world.*;
import eu.mshade.enderman.packet.login.PacketOutEncryption;
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.*;
import eu.mshade.mwork.MOptional;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EndermanSession implements EnderFrameSession {

    private static final Random random = new Random();

    private EnderFrameSessionHandler enderFrameSessionHandler;
    private ProtocolVersion protocolVersion = ProtocolVersion.UNKNOWN;
    private GameProfile gameProfile;
    private GameMode gameMode;
    private SocketAddress socketAddress;
    private final String sessionId;
    private Location location;
    private final byte[] verifyToken = new byte[4];
    private MOptional<String> displayName = MOptional.empty();
    private Queue<ChunkBuffer> observeChunks = new ConcurrentLinkedQueue<>();
    private Player player;
    private int ping = 0;

    public EndermanSession(EnderFrameSessionHandler enderFrameSessionHandler) {
        this.sessionId = Long.toString(random.nextLong(), 16).trim();
        this.enderFrameSessionHandler = enderFrameSessionHandler;
        this.socketAddress = enderFrameSessionHandler.getChannel().remoteAddress();
        random.nextBytes(verifyToken);
    }


    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(Player player) {
        if(this.player != null || player != null)
            this.player = player;
    }

    @Override
    public MOptional<String> getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(MOptional<String> displayName) {
        this.displayName = displayName;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public GameProfile getGameProfile() {
        return gameProfile;
    }

    @Override
    public void setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    @Override
    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    @Override
    public EnderFrameSessionHandler getEnderFrameSessionHandler() {
        return enderFrameSessionHandler;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public int getPing() {
        return ping;
    }

    @Override
    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Collection<ChunkBuffer> getChunkBuffers() {
        return observeChunks;
    }

    @Override
    public void sendKeepAlive(int threshold) {
        enderFrameSessionHandler.sendPacket(new PacketOutKeepAlive(threshold));
    }

    @Override
    public void sendPlayerInfo(PlayerInfoBuilder playerInfoBuilder) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutPlayerInfo(playerInfoBuilder));
    }

    @Override
    public void sendEncryption(PublicKey publicKey) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEncryption(sessionId, publicKey, verifyToken));
    }

    @Override
    public void sendCompression(int threshold) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutSetCompression(threshold));
        getEnderFrameSessionHandler().enableCompression(threshold);
    }

    @Override
    public void sendLoginSuccess() {
        getEnderFrameSessionHandler().sendPacket(new PacketOutLoginSuccess(getGameProfile()));
        getEnderFrameSessionHandler().toggleProtocolStatus(ProtocolStatus.PLAY);
    }

    @Override
    public void sendJoinGame(GameMode gameMode, Dimension dimension, Difficulty difficulty, int maxPlayers, LevelType levelType, boolean reducedDebugInfo) {
        setGameMode(gameMode);
        getEnderFrameSessionHandler().sendPacket(new PacketOutJoinGame(gameMode, dimension, difficulty, maxPlayers, levelType.getName(), reducedDebugInfo));
    }

    @Override
    public void sendPosition(double x, double y, double z) {
        this.sendPosition(x, y, z, 0, 0);
    }

    @Override
    public void sendPosition(double x, double y, double z, float yaw, float pitch) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutPlayerPositionAndLook(x, y, z, yaw, pitch));
    }

    @Override
    public void sendMessage(TextComponent textComponent, TextPosition position) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutChatMessage(textComponent, position));
    }

    @Override
    public void sendDisconnect(String message) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutDisconnect(TextComponent.of(message)));
    }

    @Override
    public void sendChunk(ChunkBuffer chunkBuffer) {
        chunkBuffer.getViewers().add(this);
        observeChunks.add(chunkBuffer);
        List<SectionBuffer> sectionBuffers = new ArrayList<>();
        for (SectionBuffer sectionBuffer : chunkBuffer.getSectionBuffers()) {
            if (sectionBuffer != null && sectionBuffer.getRealBlock() != 0) {
                sectionBuffers.add(sectionBuffer);
            }
        }
        int capacity = sectionBuffers.size()*(4096*4)+256;

        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        for (SectionBuffer sectionBuffer : sectionBuffers) {
            for (int i = 0; i < 4096; i++) {
                byteBuffer.put((byte) (sectionBuffer.getBlocks()[i] << 4 | sectionBuffer.getData().get(i)));
                byteBuffer.put((byte) (sectionBuffer.getBlocks()[i] >> 4));
            }
        }

        for (SectionBuffer sectionBuffer : sectionBuffers) {
            byteBuffer.put(sectionBuffer.getBlockLight().getRawData());
        }

        for (SectionBuffer sectionBuffer : sectionBuffers) {
            byteBuffer.put(sectionBuffer.getSkyLight().getRawData());
        }

        byteBuffer.put(chunkBuffer.getBiomes());

        getEnderFrameSessionHandler().sendPacket(new PacketOutChunkData(chunkBuffer.getX(), chunkBuffer.getZ(), true, chunkBuffer.getBitMask(), byteBuffer.array()));
    }

    @Override
    public void sendUnloadChunk(ChunkBuffer chunkBuffer) {
        chunkBuffer.getViewers().remove(this);
        chunkBuffer.getEntities().forEach(chunkBuffer::removeEntity);

        observeChunks.remove(chunkBuffer);
        getEnderFrameSessionHandler().sendPacket(new PacketOutChunkData(chunkBuffer.getX(), chunkBuffer.getZ(), true, 0, new byte[0]));
    }

    @Override
    public void sendMetadata(Entity entity, MetadataMeaning...metadataMeanings) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityMetadata(entity, metadataMeanings));
    }

    @Override
    public void sendMob(Entity entity) {
        EntityRepository repository = getEnderFrameSessionHandler().getEnderFrameProtocol().getEntityRepository();
        int id = repository.getIdByEntityType(entity.getType());
        getEnderFrameSessionHandler().sendPacket(new PacketOutSpawnMob(id, entity));
    }

    @Override
    public void sendPlayer(Player player) {
        getEnderFrameSessionHandler()
                .sendPacket(new PacketOutSpawnPlayer(player));
    }

    @Override
    public void moveTo(Player player, PacketMoveType packetMoveType, Location now, Location before, boolean ground) {
        boolean teleport = hasOverflow(floor(now.getX() * 32) - floor(before.getX() * 32)) || hasOverflow(floor(now.getY() * 32) - floor(before.getY() * 32)) || hasOverflow(floor(now.getZ() * 32) - floor(before.getZ() * 32));

        if (packetMoveType == PacketMoveType.LOOK || packetMoveType == PacketMoveType.POSITION_AND_LOOK) {
            player.getViewers().forEach(target -> {
                target.getEnderFrameSessionHandler().getEnderFrameSession().sendLook(player.getEntityId(), now.getYaw(), now.getPitch(), ground);
                target.getEnderFrameSessionHandler().getEnderFrameSession().sendHeadLook(player.getEntityId(), now.getYaw());
            });
        }
        if (!teleport) {
            if (packetMoveType == PacketMoveType.POSITION_AND_LOOK) {
                player.getViewers().forEach(target -> {
                    target.getEnderFrameSessionHandler().getEnderFrameSession().sendMoveAndLook(player.getEntityId(), now, before, ground);
                });
            } else if (packetMoveType.equals(PacketMoveType.POSITION)) {
                player.getViewers().forEach(target -> target.getEnderFrameSessionHandler().getEnderFrameSession().sendMove(player.getEntityId(), now, before, ground));
            }
        } else {
            player.getViewers().forEach(target ->{
                target.getEnderFrameSessionHandler().getEnderFrameSession().sendTeleport(player, ground);
            });
        }
    }

    private boolean hasOverflow(int value) {
        return value > 3 || value < -3;
    }

    private int floor(double d0) {
        int i = (int) d0;

        return d0 < (double) i ? i - 1 : i;
    }

    @Override
    public void sendTeleport(Entity entity, boolean onGround) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityTeleport(entity,onGround));
    }

    @Override
    public void sendMove(int entityId, Location now, Location before, boolean onGround) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityRelativeMove(entityId, now, before, onGround));
    }

    @Override
    public void sendMoveAndLook(int entityId, Location now, Location before, boolean onGround) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityLookRelativeMove(entityId, now, before, onGround));
    }

    @Override
    public void sendLook(int entityId, float yaw, float pitch, boolean onGround) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityLook(entityId, yaw, pitch, onGround));
    }

    @Override
    public void sendHeadLook(int entityId, float headYaw) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutEntityHeadLook(entityId, headYaw));
    }

    @Override
    public void removeEntities(Entity...entities) {
        getEnderFrameSessionHandler().sendPacket(new PacketOutDestroyEntities(entities));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndermanSession that = (EndermanSession) o;
        return Objects.equals(enderFrameSessionHandler, that.enderFrameSessionHandler) && protocolVersion == that.protocolVersion && Objects.equals(gameProfile, that.gameProfile) && Objects.equals(socketAddress, that.socketAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enderFrameSessionHandler, protocolVersion, gameProfile, socketAddress);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("FLUSH");
    }
}
