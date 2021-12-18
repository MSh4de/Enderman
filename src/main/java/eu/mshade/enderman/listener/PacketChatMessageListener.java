package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.packetevent.PacketChatMessageEvent;
import eu.mshade.enderman.packet.play.PacketInChatMessage;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketChatMessageListener implements EventListener<PacketInChatMessage> {

    @Override
    public void onEvent(PacketInChatMessage event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publishAsync(new PacketChatMessageEvent(eventContainer.getContainer(EnderFrameSessionHandler.class).getEnderFrameSession().getPlayer(),
                event.getMessage()), eventContainer);
    }

}
