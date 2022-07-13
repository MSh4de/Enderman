package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInChatMessage implements PacketIn {

    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.message = protocolBuffer.readString();
    }
}
