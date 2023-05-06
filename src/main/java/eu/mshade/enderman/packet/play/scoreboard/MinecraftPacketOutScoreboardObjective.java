package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardAction;

public class MinecraftPacketOutScoreboardObjective implements MinecraftPacketOut {

    private final Scoreboard scoreboard;
    private final ScoreboardAction scoreboardAction;

    public MinecraftPacketOutScoreboardObjective(Scoreboard scoreboard, ScoreboardAction scoreboardAction) {
        this.scoreboard = scoreboard;
        this.scoreboardAction = scoreboardAction;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(scoreboard.getId());
        minecraftByteBuf.writeByte(scoreboardAction.getMode());

        if (scoreboardAction == ScoreboardAction.CREATE || scoreboardAction == ScoreboardAction.UPDATE_DISPLAY_TEXT) {
            String text = scoreboard.getTitle().toLegacyText();
            if (text.length() > 32) {
                throw new IndexOutOfBoundsException(text + " is bigger than 32 letters");
            }

            minecraftByteBuf.writeString(text);
            minecraftByteBuf.writeString(scoreboard.getScoreboardType().getType());
        }
    }
}
