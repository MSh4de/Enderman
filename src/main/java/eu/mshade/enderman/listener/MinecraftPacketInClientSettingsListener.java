package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketClientSettingsEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInClientSettings;
import eu.mshade.mwork.event.EventListener;

public class MinecraftPacketInClientSettingsListener implements EventListener<MinecraftPacketInClientSettings> {

    @Override
    public void onEvent(MinecraftPacketInClientSettings event) {
        EnderFrame.get().getPacketEventBus().publishAsync(
                new MinecraftPacketClientSettingsEvent(event.getLocale(), event.getViewDistance(), event.getChatMode(),
                        event.isChatColors(), event.getSkinParts()));
    }
}
