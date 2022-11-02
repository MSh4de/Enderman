package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketKeepAliveEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInKeepAlive;
import eu.mshade.mwork.event.EventListener;

public class PacketKeepAliveListener implements EventListener<MinecraftPacketInKeepAlive> {

    @Override
    public void onEvent(MinecraftPacketInKeepAlive event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new PacketKeepAliveEvent(event.getSessionWrapper(), event.getThreshold()));
    }
}
