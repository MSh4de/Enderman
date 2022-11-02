package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInPlayerGround implements MinecraftPacketIn {

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
