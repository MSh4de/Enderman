package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketEntityActionEvent;
import eu.mshade.enderman.packet.play.PacketInEntityAction;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketEntityActionListener implements EventListener<PacketInEntityAction> {
    @Override
    public void onEvent(PacketInEntityAction event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new PacketEntityActionEvent(event.getSessionWrapper().getPlayer(), event.getActionType(), event.getActionParameter()));
    }
}
