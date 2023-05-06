package eu.mshade.enderman.packet.play.scoreboard;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.scoreboard.Scoreboard;

public class MinecraftPacketOutDisplayScoreboard implements MinecraftPacketOut {

    private final Scoreboard scoreboard;

    public MinecraftPacketOutDisplayScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        try {
            minecraftByteBuf.writeByte(scoreboard.getScoreboardPosition().getPosition());

            if (scoreboard.getId().length() > 16) {
                throw new IndexOutOfBoundsException(scoreboard.getId() + " is bigger than 16 letters");
            }

            minecraftByteBuf.writeString(scoreboard.getId());

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
