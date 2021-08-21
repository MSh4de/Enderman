package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityRelativeMove extends PacketOut {

    private int eid;
    private byte x, y, z;
    private boolean ground;

    public PacketOutEntityRelativeMove(int eid, byte x, byte y, byte z, boolean ground) {
        this.eid = eid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ground = ground;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(eid);
        byteMessage.writeByte(x);
        byteMessage.writeByte(y);
        byteMessage.writeByte(z);
        byteMessage.writeBoolean(ground);
    }
}
