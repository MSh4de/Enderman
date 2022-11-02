package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class MinecraftPacketOutChatMessage implements MinecraftPacketOut {

    private TextComponent textComponent;
    private TextPosition position;

    public MinecraftPacketOutChatMessage(TextComponent textComponent, TextPosition position) {
        this.textComponent = textComponent;
        this.position = position;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeValueAsString(textComponent);
        protocolBuffer.writeByte(position.getId());
    }
}
