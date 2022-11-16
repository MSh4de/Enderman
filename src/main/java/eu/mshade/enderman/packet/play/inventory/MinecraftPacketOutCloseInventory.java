package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutCloseInventory implements MinecraftPacketOut {

    private int id;

    public MinecraftPacketOutCloseInventory(int id) {
        this.id = id;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeByte(id);
    }

}
