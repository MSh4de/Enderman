package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutKeepAlive implements PacketOut {

    private final int threshold;

    public PacketOutKeepAlive(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(threshold);
    }
}
