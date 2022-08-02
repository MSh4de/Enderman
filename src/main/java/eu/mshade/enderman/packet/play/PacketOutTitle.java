package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.title.Title;
import eu.mshade.enderframe.title.TitleAction;

public class PacketOutTitle implements PacketOut {

    private final TitleAction titleAction;
    private final Title title;

    public PacketOutTitle(TitleAction titleAction, Title title) {
        this.titleAction = titleAction;
        this.title = title;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(titleAction.getAction());

        switch (titleAction) {
            case SET_TITLE -> {
                protocolBuffer.writeValueAsString(title.getTitle());
            }
            case SET_SUBTITLE -> {
                protocolBuffer.writeValueAsString(title.getSubtitle());
            }
            case SET_TIME -> {
                protocolBuffer.writeInt(title.getTitleTime().getFadeIn());
                protocolBuffer.writeInt(title.getTitleTime().getStay());
                protocolBuffer.writeInt(title.getTitleTime().getFadeOut());
            }
        }
    }
}
