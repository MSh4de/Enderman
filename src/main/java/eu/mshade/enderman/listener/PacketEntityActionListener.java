package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketEntityActionEvent;
import eu.mshade.enderman.packet.play.PacketInEntityAction;
import eu.mshade.mwork.event.EventContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketEntityActionListener implements EventListener<PacketInEntityAction> {
    @Override
    public void onEvent(PacketInEntityAction event, EventContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketEntityActionEvent(event.getActionType(), event.getActionParameter()), eventContainer);
    }
}
