package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInLogin implements MinecraftPacketIn {

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
