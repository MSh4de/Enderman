package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;

public class PacketInPlayerLook extends PacketInPlayerGround {

    private float yaw, pith;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.yaw = byteMessage.readFloat();
        this.pith = byteMessage.readFloat();
        super.deserialize(byteMessage);
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
