package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInCloseInventory implements MinecraftPacketIn {

    private int id;
    private SessionWrapper sessionWrapper;


    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.id = protocolBuffer.readByte();
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public int getId() {
        return id;
    }
}
