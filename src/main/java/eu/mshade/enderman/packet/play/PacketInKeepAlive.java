package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInKeepAlive implements PacketIn {

    private int threshold;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        threshold = protocolBuffer.readVarInt();
    }

    public int getThreshold() {
        return threshold;
    }

    @Override
    public String toString() {
        return "PacketInKeepAlive{" +
                "threshold=" + threshold +
                '}';
    }
}
