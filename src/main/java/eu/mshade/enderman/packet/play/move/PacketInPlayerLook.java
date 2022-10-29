package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class PacketInPlayerLook extends PacketInPlayerGround {

    private SessionWrapper sessionWrapper;
    private float yaw, pith;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.yaw = protocolBuffer.readFloat() % 360;
        this.pith = protocolBuffer.readFloat() % 360;
        super.deserialize(sessionWrapper, protocolBuffer);
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPith() {
        return pith;
    }

    @Override
    public String toString() {
        return "PacketInPlayerLook{" +
                "yaw=" + yaw +
                ", pith=" + pith +
                ", onGround="+ this.isOnGround() +
                '}';
    }
}
