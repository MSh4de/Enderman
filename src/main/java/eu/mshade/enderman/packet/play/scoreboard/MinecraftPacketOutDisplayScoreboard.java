package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.scoreboard.Scoreboard;

public class MinecraftPacketOutDisplayScoreboard implements MinecraftPacketOut {

    private final Scoreboard scoreboard;

    public MinecraftPacketOutDisplayScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(scoreboard.getScoreboardPosition().getPosition());

        if (scoreboard.getScoreboardId().length() > 16) {
            throw new IndexOutOfBoundsException(scoreboard.getScoreboardId() + " is bigger than 16 letters");
        }

        protocolBuffer.writeString(scoreboard.getScoreboardId());
    }
}
