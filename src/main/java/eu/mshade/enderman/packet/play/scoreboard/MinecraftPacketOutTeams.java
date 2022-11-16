package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.team.Team;

public class MinecraftPacketOutTeams implements MinecraftPacketOut {

    private final Team team;

    public MinecraftPacketOutTeams(Team team) {
        this.team = team;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(team.getTeamName());
        minecraftByteBuf.writeByte(team.getTeamMode().getMode());

        switch (team.getTeamMode()) {
            case CREATE_TEAM -> {
                minecraftByteBuf.writeString(team.getTeamDisplayName());
                minecraftByteBuf.writeString(team.getTeamPrefix());
                minecraftByteBuf.writeString(team.getTeamSuffix());
                minecraftByteBuf.writeByte(team.getTeamFriendlyFire().getFriendlyFire());
                minecraftByteBuf.writeString(team.getTeamNameTagVisibility().getNameTagVisibility());
                minecraftByteBuf.writeByte(team.getTeamColor().ordinal());
                minecraftByteBuf.writeVarInt(team.getPlayerCount());

                team.getPlayersName().forEach(minecraftByteBuf::writeString);
            }
            case UPDATE_TEAM_INFOS -> {
                minecraftByteBuf.writeString(team.getTeamPrefix());
                minecraftByteBuf.writeString(team.getTeamSuffix());
                minecraftByteBuf.writeByte(team.getTeamFriendlyFire().getFriendlyFire());
                minecraftByteBuf.writeString(team.getTeamNameTagVisibility().getNameTagVisibility());
                minecraftByteBuf.writeByte(14);
            }
            case ADD_PLAYER, REMOVE_PLAYER ->  {
                minecraftByteBuf.writeVarInt(team.getPlayerCount());

                team.getPlayersName().forEach(minecraftByteBuf::writeString);
            }
        }
    }
}
