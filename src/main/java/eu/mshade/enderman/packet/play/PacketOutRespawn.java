package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Difficulty;
import eu.mshade.enderframe.world.Dimension;

public class PacketOutRespawn implements PacketOut {

    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final String levelType;

    public PacketOutRespawn(GameMode gameMode, Dimension dimension, Difficulty difficulty, String levelType) {
        this.gameMode = gameMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.levelType = levelType;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeInt(dimension.getId());
        protocolBuffer.writeByte(difficulty.getId());
        protocolBuffer.writeByte(gameMode.getId());
        protocolBuffer.writeString(levelType);
    }
}
