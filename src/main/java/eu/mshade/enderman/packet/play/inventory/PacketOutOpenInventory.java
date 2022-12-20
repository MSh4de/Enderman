package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutOpenInventory implements PacketOut {

    protected Inventory inventory;
    protected int id;
    protected String name;
    protected int size;

    public PacketOutOpenInventory(String name, int size, int id, Inventory inventory) {
        this.inventory = inventory;
        this.id = id;
        this.name = name;
        this.size = size;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(id);
        protocolBuffer.writeString(name);
        protocolBuffer.writeValueAsString(inventory.getTextComponent());
        protocolBuffer.writeByte(size);


    }
}
