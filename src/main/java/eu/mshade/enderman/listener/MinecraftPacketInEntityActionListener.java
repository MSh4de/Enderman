package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketEntityActionEvent;
import eu.mshade.enderman.packet.play.entity.MinecraftPacketInEntityAction;
import eu.mshade.mwork.event.EventListener;

public class MinecraftPacketInEntityActionListener implements EventListener<MinecraftPacketInEntityAction> {
    @Override
    public void onEvent(MinecraftPacketInEntityAction event) {
        EnderFrame.get().getPacketEvents().publishAsync(new MinecraftPacketEntityActionEvent(event.getMinecraftSession().getPlayer(), event.getActionType(), event.getActionParameter()));
    }
}
