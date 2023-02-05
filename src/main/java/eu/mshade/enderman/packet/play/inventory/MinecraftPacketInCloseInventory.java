package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInCloseInventory implements MinecraftPacketIn {

    private int id;
    private MinecraftSession minecraftSession;


    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.id = minecraftByteBuf.readByte();
    }

    @Override
    public MinecraftSession getMinecraftSession() {
        return minecraftSession;
    }

    public int getId() {
        return id;
    }
}
