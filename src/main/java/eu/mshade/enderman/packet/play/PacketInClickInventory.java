package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInClickInventory implements PacketIn {


    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        byte id = protocolBuffer.readByte();
        short slot = protocolBuffer.readShort();

        System.out.println("id: "+id+", slot: "+slot);

    }
}
