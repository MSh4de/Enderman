package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardMode;

public class MinecraftPacketOutScoreboardObjective implements MinecraftPacketOut {

    private final Scoreboard scoreboard;
    private final ScoreboardMode scoreboardMode;

    public MinecraftPacketOutScoreboardObjective(Scoreboard scoreboard, ScoreboardMode scoreboardMode) {
        this.scoreboard = scoreboard;
        this.scoreboardMode = scoreboardMode;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(scoreboard.getScoreboardId());
        minecraftByteBuf.writeByte(scoreboardMode.getMode());

        if (scoreboardMode == ScoreboardMode.CREATE || scoreboardMode == ScoreboardMode.UPDATE_DISPLAY_TEXT) {
            if (scoreboard.getScoreboardName().toLegacyText().length() > 32) {
                throw new IndexOutOfBoundsException(scoreboard.getScoreboardName().toLegacyText() + " is bigger than 32 letters");
            }

            minecraftByteBuf.writeString(scoreboard.getScoreboardName().toLegacyText());
            minecraftByteBuf.writeString(scoreboard.getScoreboardType().getType());
        }
    }
}
