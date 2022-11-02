package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.SkinPart;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInClientSettings implements MinecraftPacketIn {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColors;
    private SkinPart skinPart;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.locale = protocolBuffer.readString();
        this.viewDistance = protocolBuffer.readByte();
        this.chatMode = protocolBuffer.readByte();
        this.chatColors = protocolBuffer.readBoolean();
        this.skinPart = SkinPart.fromByte((byte) protocolBuffer.readUnsignedByte());
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
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }
}
