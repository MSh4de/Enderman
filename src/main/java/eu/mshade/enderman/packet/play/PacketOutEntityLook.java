package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutEntityLook extends PacketOut {
    private final int id;
    private final float yaw,pitch;
    private final boolean isOnGround;


    public PacketOutEntityLook(int id, float yaw, float pitch, boolean isOnGround) {
        this.id = id;
        this.yaw = yaw;
        this.pitch = pitch;
        this.isOnGround = isOnGround;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte((int) yaw);
        byteMessage.writeByte((int) pitch);
        byteMessage.writeBoolean(isOnGround);
    }
}
