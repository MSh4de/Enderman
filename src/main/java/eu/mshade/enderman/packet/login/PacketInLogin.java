package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInLogin implements PacketIn {

    private String name;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        name = protocolBuffer.readString();
    }

    public String getName() {
        return name;
    }
}
