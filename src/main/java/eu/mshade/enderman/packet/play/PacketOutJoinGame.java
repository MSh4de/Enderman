package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Difficulty;
import eu.mshade.enderframe.world.Dimension;

public class PacketOutJoinGame extends PacketOut {

    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final int maxPlayers;
    private final String levelType;
    private final boolean reducedDebugInfo;

    public PacketOutJoinGame(GameMode gameMode, Dimension dimension, Difficulty difficulty, int maxPlayers, String levelType, boolean reducedDebugInfo) {
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeInt(0);
        byteMessage.writeByte(gameMode.getId());
        byteMessage.writeByte(dimension.getId());
        byteMessage.writeByte(difficulty.getId());
        byteMessage.writeByte(maxPlayers);
        byteMessage.writeString(levelType);
        byteMessage.writeBoolean(reducedDebugInfo);
    }

}
