package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Vector;

import java.util.List;

public class MinecraftPacketOutUpdateSign implements MinecraftPacketOut {

    private Vector vector;
    private List<TextComponent> textComponents;

    public MinecraftPacketOutUpdateSign(Vector vector, List<TextComponent> textComponents) {
        this.vector = vector;
        this.textComponents = textComponents;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeBlockPosition(vector);

        for (int i = 0; i < 4; i++) {
            if (textComponents.size() - 1 >= i) {
                TextComponent textComponent = textComponents.get(i);
                minecraftByteBuf.writeValueAsString(textComponent == null ? TextComponent.empty() : textComponent);
            } else {
                minecraftByteBuf.writeValueAsString(TextComponent.empty());
            }
        }
    }
}
