package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardLine;
import eu.mshade.enderframe.scoreboard.ScoreboardLineAction;

public class MinecraftPacketOutUpdateScoreboard implements MinecraftPacketOut {

    private final Scoreboard scoreboard;
    private final ScoreboardLine scoreboardLine;
    private final ScoreboardLineAction scoreboardLineAction;

    public MinecraftPacketOutUpdateScoreboard(Scoreboard scoreboard, ScoreboardLine scoreboardLine, ScoreboardLineAction scoreboardLineAction) {
        this.scoreboard = scoreboard;
        this.scoreboardLine = scoreboardLine;
        this.scoreboardLineAction = scoreboardLineAction;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        String objectiveName = scoreboardLine.getValue();

/*        if (scoreboardObjective.getScoreboard() instanceof EntityScoreboard) {
            if (scoreboardObjective.getObjective() instanceof Player player) {
                objectiveName = player.getName();
            } else {
                Entity entity = (Entity) scoreboardObjective.getObjective();
                objectiveName = entity.getUniqueId().toString();
            }
        } else {
            objectiveName = (String) scoreboardObjective.getObjective();
        }*/

        if (objectiveName.length() > 40) {
            throw new IndexOutOfBoundsException(objectiveName + " is bigger than 40 letters");
        }

        minecraftByteBuf.writeString(objectiveName);
        minecraftByteBuf.writeVarInt(scoreboardLineAction.getAction());
        minecraftByteBuf.writeString(scoreboard.getId());

        if (scoreboardLineAction == ScoreboardLineAction.CHANGE) {
            minecraftByteBuf.writeVarInt(scoreboardLine.getScore());
        }
    }
}
