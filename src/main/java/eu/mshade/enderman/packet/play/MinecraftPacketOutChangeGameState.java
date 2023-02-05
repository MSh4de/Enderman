package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

/**
 * permit to pick block
 */
public class MinecraftPacketOutChangeGameState implements MinecraftPacketOut {

    private int reason;
    private float value;

    public MinecraftPacketOutChangeGameState(int reason, float value) {
        this.reason = reason;
        this.value = value;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        /*
        protocolBuffer.writeByte(3);
        protocolBuffer.writeFloat(GameMode.SURVIVAL.getId());
         */
        minecraftByteBuf.writeByte(reason);
        minecraftByteBuf.writeFloat(value);
    }
}
