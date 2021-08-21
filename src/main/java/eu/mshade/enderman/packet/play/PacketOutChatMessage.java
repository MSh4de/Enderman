package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutChatMessage extends PacketOut {

    private TextComponent textComponent;
    private TextPosition position;

    public PacketOutChatMessage(TextComponent textComponent, TextPosition position) {
        this.textComponent = textComponent;
        this.position = position;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeValueAsString(textComponent);
        byteMessage.writeByte(position.getId());
    }
}
