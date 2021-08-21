package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.Vector;

public class PacketOutEntityVelocity extends PacketOut {

    private int eid;
    private Vector vector;

    public PacketOutEntityVelocity(int eid, Vector vector) {
        this.eid = eid;
        this.vector = vector;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(eid);
        byteMessage.writeShort(((short) (vector.getX() * 8000)));
        byteMessage.writeShort(((short) (vector.getY() * 8000)));
        byteMessage.writeShort(((short) (vector.getZ() * 8000)));
    }
}
