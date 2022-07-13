package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInPlayerLook extends PacketInPlayerGround {

    private float yaw, pith;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.yaw = protocolBuffer.readFloat();
        this.pith = protocolBuffer.readFloat();
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
