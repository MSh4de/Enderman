package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInChatMessage implements MinecraftPacketIn {

    private String message;
    private MinecraftSession minecraftSession;

    public String getMessage() {
        return message;
    }

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.message = minecraftByteBuf.readString();
        this.minecraftSession = minecraftSession;
    }

    @Override
    public MinecraftSession getMinecraftSession() {
        return minecraftSession;
    }
}
