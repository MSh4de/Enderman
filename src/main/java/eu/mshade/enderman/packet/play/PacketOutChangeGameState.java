package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

/**
 * permit to pick block
 */
public class PacketOutChangeGameState implements PacketOut {

    private int reason;
    private float value;

    public PacketOutChangeGameState(int reason, float value) {
        this.reason = reason;
        this.value = value;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        /*
        protocolBuffer.writeByte(3);
        protocolBuffer.writeFloat(GameMode.SURVIVAL.getId());
         */
        protocolBuffer.writeByte(reason);
        protocolBuffer.writeFloat(value);
    }
}
