package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutChatMessage implements PacketOut {

    private TextComponent textComponent;
    private TextPosition position;

    public PacketOutChatMessage(TextComponent textComponent, TextPosition position) {
        this.textComponent = textComponent;
        this.position = position;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeValueAsString(textComponent);
        protocolBuffer.writeByte(position.getId());
    }
}
