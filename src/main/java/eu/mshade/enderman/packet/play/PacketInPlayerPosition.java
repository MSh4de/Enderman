package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;

public class PacketInPlayerPosition extends PacketInPlayerGround {

    private double x, y, z;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.x = byteMessage.readDouble();
        this.y = byteMessage.readDouble();
        this.z = byteMessage.readDouble();
        super.deserialize(byteMessage);
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
        return "PacketInPlayerPosition{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", onGround="+ this.isOnGround() +
                '}';
    }
}
