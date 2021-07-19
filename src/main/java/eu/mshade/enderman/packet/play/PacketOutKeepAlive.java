package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutKeepAlive extends PacketOut {

    private final int threshold;

    public PacketOutKeepAlive(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(threshold);
    }
}
