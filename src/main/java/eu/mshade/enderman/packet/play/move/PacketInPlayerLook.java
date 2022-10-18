package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInPlayerLook extends PacketInPlayerGround {

    private float yaw, pith;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.yaw = protocolBuffer.readFloat() % 360;
        this.pith = protocolBuffer.readFloat() % 360;
        super.deserialize(protocolBuffer);
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
