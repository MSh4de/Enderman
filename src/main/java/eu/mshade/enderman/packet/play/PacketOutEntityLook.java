package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityLook extends PacketOut {
    private final int id;
    private final byte yaw,pitch;
    private final boolean isOnGround;

    public PacketOutEntityLook(int id, byte yaw, byte pitch, boolean isOnGround) {
        this.id = id;
        this.yaw = yaw;
        this.pitch = pitch;
        this.isOnGround = isOnGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte(yaw);
        byteMessage.writeByte(pitch);
        byteMessage.writeBoolean(isOnGround);
    }
}
