package eu.mshade.enderman.packet.play.move;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInPlayerGround implements PacketIn {

    private boolean onGround;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.onGround = protocolBuffer.readBoolean();
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
