package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInPlayerLook extends PacketInPlayerGround {

    private float yaw, pitch;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.yaw = protocolBuffer.readFloat();
        this.pitch = protocolBuffer.readFloat();
        super.deserialize(protocolBuffer);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return "PacketInPlayerLook{" +
                "yaw=" + yaw +
                ", pitch=" + pitch +
                ", onGround="+ this.isOnGround() +
                '}';
    }
}
