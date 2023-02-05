package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.title.Title;
import eu.mshade.enderframe.title.TitleAction;

public class MinecraftPacketOutTitle implements MinecraftPacketOut {

    private final TitleAction titleAction;
    private final Title title;

    public MinecraftPacketOutTitle(TitleAction titleAction, Title title) {
        this.titleAction = titleAction;
        this.title = title;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(titleAction.getAction());

        switch (titleAction) {
            case SET_TITLE -> {
                minecraftByteBuf.writeValueAsString(title.getTitle());
            }
            case SET_SUBTITLE -> {
                minecraftByteBuf.writeValueAsString(title.getSubtitle());
            }
            case SET_TIME -> {
                minecraftByteBuf.writeInt(title.getTitleTime().getFadeIn());
                minecraftByteBuf.writeInt(title.getTitleTime().getStay());
                minecraftByteBuf.writeInt(title.getTitleTime().getFadeOut());
            }
        }
    }
}
