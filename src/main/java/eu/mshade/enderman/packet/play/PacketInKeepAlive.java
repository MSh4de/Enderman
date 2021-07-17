package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInKeepAlive extends PacketIn {

    private int threshold;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        threshold = byteMessage.readVarInt();
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
