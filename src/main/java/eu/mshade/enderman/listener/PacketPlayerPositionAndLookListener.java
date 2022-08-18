package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketMoveAndLookEvent;
import eu.mshade.enderman.packet.play.PacketInPlayerPositionAndLook;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionAndLookListener implements EventListener<PacketInPlayerPositionAndLook> {

    @Override
    public void onEvent(PacketInPlayerPositionAndLook event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publishAsync(
                new DefaultPacketMoveAndLookEvent(event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()),
                eventContainer);
    }

}
