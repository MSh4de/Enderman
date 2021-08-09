package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityRelativeMove extends PacketOut {
    private final int id;
    private final byte x,y,z;
    private final boolean onGround;

    public PacketOutEntityRelativeMove(int id, byte x, byte y, byte z, boolean onGround) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
    }
    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte(x);
        byteMessage.writeByte(y);
        byteMessage.writeByte(z);
        byteMessage.writeBoolean(onGround);
    }
}
