package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

/**
 * permit to pick block
 */
public class PacketOutChangeGameState implements PacketOut {


    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(3);
        protocolBuffer.writeFloat(GameMode.SURVIVAL.getId());
    }
}
