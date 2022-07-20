package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInCloseInventory implements PacketIn {

    private int id;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.id = protocolBuffer.readByte();
    }

    public int getId() {
        return id;
    }
}
