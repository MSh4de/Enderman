package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.EntityScoreboard;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjective;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjectiveAction;

public class MinecraftPacketOutUpdateScoreboard implements MinecraftPacketOut {

    private final ScoreboardObjective<?> scoreboardObjective;
    private final ScoreboardObjectiveAction scoreboardObjectiveAction;

    public MinecraftPacketOutUpdateScoreboard(ScoreboardObjective<?> scoreboardObjective, ScoreboardObjectiveAction scoreboardObjectiveAction) {
        this.scoreboardObjective = scoreboardObjective;
        this.scoreboardObjectiveAction = scoreboardObjectiveAction;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        String objectiveName;

        if (scoreboardObjective.getScoreboard() instanceof EntityScoreboard) {
            if (scoreboardObjective.getObjective() instanceof Player player) {
                objectiveName = player.getName();
            } else {
                Entity entity = (Entity) scoreboardObjective.getObjective();
                objectiveName = entity.getUniqueId().toString();
            }
        } else {
            objectiveName = (String) scoreboardObjective.getObjective();
        }

        if (objectiveName.length() > 40) {
            throw new IndexOutOfBoundsException(objectiveName + " is bigger than 40 letters");
        }

        minecraftByteBuf.writeString(objectiveName);
        minecraftByteBuf.writeVarInt(scoreboardObjectiveAction.ordinal());
        minecraftByteBuf.writeString(scoreboardObjective.getScoreboard().getScoreboardId());

        if (scoreboardObjectiveAction == ScoreboardObjectiveAction.CREATE_OR_UPDATE) {
            minecraftByteBuf.writeVarInt(scoreboardObjective.getObjectiveValue());
        }
    }
}
