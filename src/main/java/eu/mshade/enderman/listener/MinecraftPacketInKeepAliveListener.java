package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketKeepAliveEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInKeepAlive;
import eu.mshade.mwork.event.EventListener;

public class MinecraftPacketInKeepAliveListener implements EventListener<MinecraftPacketInKeepAlive> {

    @Override
    public void onEvent(MinecraftPacketInKeepAlive event) {
        EnderFrame.get().getPacketEvents().publishAsync(new MinecraftPacketKeepAliveEvent(event.getMinecraftSession(), event.getThreshold()));
    }
}
