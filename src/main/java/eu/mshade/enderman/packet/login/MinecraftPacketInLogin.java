package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInLogin implements MinecraftPacketIn {

    private MinecraftSession minecraftSession;
    private String name;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.name = minecraftByteBuf.readString();
        this.minecraftSession = minecraftSession;
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    public String getName() {
        return name;
    }
}
