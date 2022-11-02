package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInKeepAlive implements MinecraftPacketIn {

    private int threshold;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        threshold = protocolBuffer.readVarInt();
    }

    public int getThreshold() {
        return threshold;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    @Override
    public String toString() {
        return "PacketInKeepAlive{" +
                "threshold=" + threshold +
                '}';
    }
}
