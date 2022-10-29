package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketClientSettingsEvent;
import eu.mshade.enderman.packet.play.PacketInClientSettings;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketClientSettingsListener implements EventListener<PacketInClientSettings> {

    @Override
    public void onEvent(PacketInClientSettings event) {
        EnderFrame.get().getPacketEventBus().publishAsync(
                new PacketClientSettingsEvent(event.getLocale(), event.getViewDistance(), event.getChatMode(),
                        event.isChatColors(), event.getSkinParts()));
    }
}
