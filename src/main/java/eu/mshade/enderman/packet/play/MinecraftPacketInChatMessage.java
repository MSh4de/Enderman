package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInChatMessage implements MinecraftPacketIn {

    private String message;
    private SessionWrapper sessionWrapper;

    public String getMessage() {
        return message;
    }

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.message = protocolBuffer.readString();
        this.sessionWrapper = sessionWrapper;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }
}
