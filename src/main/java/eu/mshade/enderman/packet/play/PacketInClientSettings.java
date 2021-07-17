package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInClientSettings extends PacketIn {

    private String locale;
    private byte viewDistance;
    private byte chatMode;
    private boolean chatColors;
    private short displayedSkinParts;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.locale = byteMessage.readString();
        this.viewDistance = byteMessage.readByte();
        this.chatMode = byteMessage.readByte();
        this.chatColors = byteMessage.readBoolean();
        this.displayedSkinParts = byteMessage.readUnsignedByte();
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

    public short getDisplayedSkinParts() {
        return displayedSkinParts;
    }


}
