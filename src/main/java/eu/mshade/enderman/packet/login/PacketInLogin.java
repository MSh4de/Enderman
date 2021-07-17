package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInLogin extends PacketIn {

    private String name;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        name = byteMessage.readString();
    }

    public String getName() {
        return name;
    }
}
