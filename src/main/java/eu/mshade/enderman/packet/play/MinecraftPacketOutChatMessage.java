package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutChatMessage implements MinecraftPacketOut {

    private TextComponent textComponent;
    private TextPosition position;

    public MinecraftPacketOutChatMessage(TextComponent textComponent, TextPosition position) {
        this.textComponent = textComponent;
        this.position = position;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeValueAsString(textComponent);
        minecraftByteBuf.writeByte(position.getId());
    }
}
