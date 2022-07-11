package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.SkinPart;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInClientSettings implements PacketIn {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColors;
    private SkinPart skinPart;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
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
}
