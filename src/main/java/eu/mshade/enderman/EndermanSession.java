package eu.mshade.enderman;

import eu.mshade.enderframe.EnderFrameSession;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.PlayerInfoBuilder;
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
import java.util.stream.Collectors;

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
    private int ping = 0;

    public EndermanSession(EnderFrameSessionHandler enderFrameSessionHandler) {
        this.sessionId = Long.toString(random.nextLong(), 16).trim();
        this.enderFrameSessionHandler = enderFrameSessionHandler;
        this.socketAddress = enderFrameSessionHandler.getChannel().remoteAddress();
        random.nextBytes(verifyToken);
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
        observeChunks.remove(chunkBuffer);
        getEnderFrameSessionHandler().sendPacket(new PacketOutChunkData(chunkBuffer.getX(), chunkBuffer.getZ(), true, 0, new byte[0]));
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
