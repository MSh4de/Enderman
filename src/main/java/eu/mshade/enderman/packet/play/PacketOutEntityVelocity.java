package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Vector;

public class PacketOutEntityVelocity implements PacketOut {

    private int eid;
    private Vector vector;

    public PacketOutEntityVelocity(int eid, Vector vector) {
        this.eid = eid;
        this.vector = vector;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(eid);
        protocolBuffer.writeShort(((short) (vector.getX() * 8000)));
        protocolBuffer.writeShort(((short) (vector.getY() * 8000)));
        protocolBuffer.writeShort(((short) (vector.getZ() * 8000)));
    }
}
