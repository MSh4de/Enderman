package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.inventory.NamedInventory;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutOpenInventory implements MinecraftPacketOut {

    protected NamedInventory inventory;
    protected int id;
    protected String name;
    protected int size;

    public MinecraftPacketOutOpenInventory(String name, int size, int id, NamedInventory inventory) {
        this.inventory = inventory;
        this.id = id;
        this.name = name;
        this.size = size;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeByte(id);
        minecraftByteBuf.writeString(name);
        minecraftByteBuf.writeValueAsString(inventory.getName());
        minecraftByteBuf.writeByte(size);


    }
}
