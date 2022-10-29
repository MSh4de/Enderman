package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class PacketInPlayerGround implements PacketIn {

    private boolean onGround;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.onGround = protocolBuffer.readBoolean();
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }


    @Override
    public String toString() {
        return "PacketInPlayerGround{" +
                "onGround=" + onGround +
                '}';
    }
}
