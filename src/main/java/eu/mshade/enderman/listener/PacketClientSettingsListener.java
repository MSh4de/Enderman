package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketClientSettingsEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInClientSettings;
import eu.mshade.mwork.event.EventListener;

public class PacketClientSettingsListener implements EventListener<MinecraftPacketInClientSettings> {

    @Override
    public void onEvent(MinecraftPacketInClientSettings event) {
        EnderFrame.get().getPacketEventBus().publishAsync(
                new PacketClientSettingsEvent(event.getLocale(), event.getViewDistance(), event.getChatMode(),
                        event.isChatColors(), event.getSkinParts()));
    }
}
