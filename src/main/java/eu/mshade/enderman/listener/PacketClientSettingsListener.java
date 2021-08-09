package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketClientSettingsEvent;
import eu.mshade.enderman.packet.play.PacketInClientSettings;
import eu.mshade.mwork.event.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketClientSettingsListener implements EventListener<PacketInClientSettings> {

    @Override
    public void onEvent(PacketInClientSettings event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(
                new PacketClientSettingsEvent(event.getLocale(), event.getViewDistance(), event.getChatMode(),
                        event.isChatColors(), event.getSkinParts()),
                eventContainer);
    }
}
