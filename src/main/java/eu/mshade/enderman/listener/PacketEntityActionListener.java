package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketEntityActionEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInEntityAction;
import eu.mshade.mwork.event.EventListener;

public class PacketEntityActionListener implements EventListener<MinecraftPacketInEntityAction> {
    @Override
    public void onEvent(MinecraftPacketInEntityAction event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new PacketEntityActionEvent(event.getSessionWrapper().getPlayer(), event.getActionType(), event.getActionParameter()));
    }
}
