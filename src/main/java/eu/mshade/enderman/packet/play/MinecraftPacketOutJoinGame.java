package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Difficulty;
import eu.mshade.enderframe.world.Dimension;

public class MinecraftPacketOutJoinGame implements MinecraftPacketOut {

    private final int entityId;
    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final int maxPlayers;
    private final String levelType;
    private final boolean reducedDebugInfo;

    public MinecraftPacketOutJoinGame(int entityId, GameMode gameMode, Dimension dimension, Difficulty difficulty, int maxPlayers, String levelType, boolean reducedDebugInfo) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeInt(entityId);
        minecraftByteBuf.writeByte(gameMode.getId());
        minecraftByteBuf.writeByte(dimension.getId());
        minecraftByteBuf.writeByte(difficulty.getId());
        minecraftByteBuf.writeByte(maxPlayers);
        minecraftByteBuf.writeString(levelType);
        minecraftByteBuf.writeBoolean(reducedDebugInfo);
    }

}
