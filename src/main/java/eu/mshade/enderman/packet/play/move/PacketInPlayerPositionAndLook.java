package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;
import eu.mshade.enderman.packet.play.move.PacketInPlayerLook;

public class PacketInPlayerPositionAndLook extends PacketInPlayerLook {

    private SessionWrapper sessionWrapper;
    private double x, y, z;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.x = protocolBuffer.readDouble();
        this.y = protocolBuffer.readDouble();
        this.z = protocolBuffer.readDouble();
        super.deserialize(sessionWrapper, protocolBuffer);
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "PacketInPlayerPositionAndLook{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw="+ this.getYaw()+
                ", pitch="+ this.getPitch()+
                ", onGround="+this.isOnGround() +
                '}';
    }
}
