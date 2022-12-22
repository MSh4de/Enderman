package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.SkinPart;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInClientSettings implements MinecraftPacketIn {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColors;
    private SkinPart skinPart;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.locale = minecraftByteBuf.readString();
        this.viewDistance = minecraftByteBuf.readByte();
        this.chatMode = minecraftByteBuf.readByte();
        this.chatColors = minecraftByteBuf.readBoolean();
        this.skinPart = SkinPart.fromByte((byte) minecraftByteBuf.readUnsignedByte());
    }

    public String getLocale() {
        return locale;
    }

    public byte getViewDistance() {
        return viewDistance;
    }

    public byte getChatMode() {
        return chatMode;
    }

    public boolean isChatColors() {
        return chatColors;
    }

    public SkinPart getSkinParts() {
        return skinPart;
    }

    @Override
    public MinecraftSession getMinecraftSession() {
        return minecraftSession;
    }
}
