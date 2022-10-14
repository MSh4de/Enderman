package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.scoreboard.EntityScoreboard;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjective;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjectiveAction;

public class PacketOutUpdateScoreboard implements PacketOut {

    private final ScoreboardObjective<?> scoreboardObjective;
    private final ScoreboardObjectiveAction scoreboardObjectiveAction;

    public PacketOutUpdateScoreboard(ScoreboardObjective<?> scoreboardObjective, ScoreboardObjectiveAction scoreboardObjectiveAction) {
        this.scoreboardObjective = scoreboardObjective;
        this.scoreboardObjectiveAction = scoreboardObjectiveAction;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
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

        protocolBuffer.writeString(objectiveName);
        protocolBuffer.writeVarInt(scoreboardObjectiveAction.ordinal());
        protocolBuffer.writeString(scoreboardObjective.getScoreboard().getScoreboardId());

        if (scoreboardObjectiveAction == ScoreboardObjectiveAction.CREATE_OR_UPDATE) {
            protocolBuffer.writeVarInt(scoreboardObjective.getObjectiveValue());
        }
    }
}
