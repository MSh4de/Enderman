package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardMode;

public class PacketOutScoreboardObjective implements PacketOut {

    private final Scoreboard scoreboard;
    private final ScoreboardMode scoreboardMode;

    public PacketOutScoreboardObjective(Scoreboard scoreboard, ScoreboardMode scoreboardMode) {
        this.scoreboard = scoreboard;
        this.scoreboardMode = scoreboardMode;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(scoreboard.getScoreboardId());
        protocolBuffer.writeByte(scoreboardMode.getMode());

        if (scoreboardMode == ScoreboardMode.CREATE || scoreboardMode == ScoreboardMode.UPDATE_DISPLAY_TEXT) {
            if (scoreboard.getScoreboardName().toLegacyText().length() > 32) {
                throw new IndexOutOfBoundsException(scoreboard.getScoreboardName().toLegacyText() + " is bigger than 32 letters");
            }

            protocolBuffer.writeString(scoreboard.getScoreboardName().toLegacyText());
            protocolBuffer.writeString(scoreboard.getScoreboardType().getType());
        }
    }
}
