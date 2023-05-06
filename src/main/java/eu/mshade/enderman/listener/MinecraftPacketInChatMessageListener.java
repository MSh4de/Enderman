package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketChatMessageEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInChatMessage;
import eu.mshade.mwork.event.EventListener;

public class MinecraftPacketInChatMessageListener implements EventListener<MinecraftPacketInChatMessage> {

    @Override
    public void onEvent(MinecraftPacketInChatMessage event) {
        EnderFrame.get().getPacketEvents().publishAsync(new MinecraftPacketChatMessageEvent(event.getMinecraftSession().getPlayer(), event.getMessage()));
    }

}
