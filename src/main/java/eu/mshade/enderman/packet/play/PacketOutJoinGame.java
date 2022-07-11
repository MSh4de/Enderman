package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Difficulty;
import eu.mshade.enderframe.world.Dimension;

public class PacketOutJoinGame implements PacketOut {

    private final int entityId;
    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final int maxPlayers;
    private final String levelType;
    private final boolean reducedDebugInfo;

    public PacketOutJoinGame(int entityId, GameMode gameMode, Dimension dimension, Difficulty difficulty, int maxPlayers, String levelType, boolean reducedDebugInfo) {
        this.entityId = entityId;
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeInt(entityId);
        protocolBuffer.writeByte(gameMode.getId());
        protocolBuffer.writeByte(dimension.getId());
        protocolBuffer.writeByte(difficulty.getId());
        protocolBuffer.writeByte(maxPlayers);
        protocolBuffer.writeString(levelType);
        protocolBuffer.writeBoolean(reducedDebugInfo);
    }

}
