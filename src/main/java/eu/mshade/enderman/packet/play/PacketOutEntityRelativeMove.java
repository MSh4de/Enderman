package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityRelativeMove extends PacketOut {
    private final int id;
    private final double x,y,z;
    private final boolean onGround;

    public PacketOutEntityRelativeMove(int id, double x, double y, double z, boolean onGround) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
    }
    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte((int) x);
        byteMessage.writeByte((int) y);
        byteMessage.writeByte((int) z);
        byteMessage.writeBoolean(onGround);
    }
}
