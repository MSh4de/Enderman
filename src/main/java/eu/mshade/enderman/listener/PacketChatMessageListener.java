package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketChatMessageEvent;
import eu.mshade.enderman.packet.play.PacketInChatMessage;
import eu.mshade.mwork.event.EventContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketChatMessageListener implements EventListener<PacketInChatMessage> {

    @Override
    public void onEvent(PacketInChatMessage event, EventContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketChatMessageEvent(event.getMessage()), eventContainer);
    }

}
