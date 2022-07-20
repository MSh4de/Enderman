package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutCloseInventory implements PacketOut {

    private int id;

    public PacketOutCloseInventory(int id) {
        this.id = id;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(id);
    }

}
