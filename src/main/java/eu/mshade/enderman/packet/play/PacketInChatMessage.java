package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInChatMessage extends PacketIn {

    private String message;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.message = byteMessage.readString();
    }

    public String getMessage() {
        return message;
    }
}
