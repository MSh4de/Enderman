package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.packetevent.PacketEntityActionEvent;
import eu.mshade.enderman.packet.play.PacketInEntityAction;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketEntityActionListener implements EventListener<PacketInEntityAction> {
    @Override
    public void onEvent(PacketInEntityAction event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publishAsync(new PacketEntityActionEvent(eventContainer.getContainer(EnderFrameSessionHandler.class).getEnderFrameSession().getPlayer(),
                event.getActionType(), event.getActionParameter()), eventContainer);
    }
}
