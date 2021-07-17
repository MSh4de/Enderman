package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInPlayerGround extends PacketIn {

    private boolean onGround;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.onGround = byteMessage.readBoolean();
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public String toString() {
        return "PacketInPlayerGround{" +
                "onGround=" + onGround +
                '}';
    }
}
