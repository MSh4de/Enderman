package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class PacketInLogin implements PacketIn {

    private SessionWrapper sessionWrapper;
    private String name;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.name = protocolBuffer.readString();
        this.sessionWrapper = sessionWrapper;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public String getName() {
        return name;
    }
}
