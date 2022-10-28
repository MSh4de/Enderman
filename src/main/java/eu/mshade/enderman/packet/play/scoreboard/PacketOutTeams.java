package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.mojang.chat.ChatColor;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.scoreboard.team.Team;

public class PacketOutTeams implements PacketOut {

    private final Team team;

    public PacketOutTeams(Team team) {
        this.team = team;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(team.getTeamName());
        protocolBuffer.writeByte(team.getTeamMode().getMode());

        switch (team.getTeamMode()) {
            case CREATE_TEAM -> {
                protocolBuffer.writeString(team.getTeamDisplayName());
                protocolBuffer.writeString(team.getTeamPrefix());
                protocolBuffer.writeString(team.getTeamSuffix());
                protocolBuffer.writeByte(team.getTeamFriendlyFire().getFriendlyFire());
                protocolBuffer.writeString(team.getTeamNameTagVisibility().getNameTagVisibility());
                protocolBuffer.writeByte(team.getTeamColor().ordinal());
                protocolBuffer.writeVarInt(team.getPlayerCount());

                team.getPlayersName().forEach(protocolBuffer::writeString);
            }
            case UPDATE_TEAM_INFOS -> {
                protocolBuffer.writeString(team.getTeamPrefix());
                protocolBuffer.writeString(team.getTeamSuffix());
                protocolBuffer.writeByte(team.getTeamFriendlyFire().getFriendlyFire());
                protocolBuffer.writeString(team.getTeamNameTagVisibility().getNameTagVisibility());
                protocolBuffer.writeByte(14);
            }
            case ADD_PLAYER, REMOVE_PLAYER ->  {
                protocolBuffer.writeVarInt(team.getPlayerCount());

                team.getPlayersName().forEach(protocolBuffer::writeString);
            }
        }
    }
}
