package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketLookEvent;
import eu.mshade.enderframe.packetevent.PacketMoveEvent;
import eu.mshade.enderframe.packetevent.PacketMoveType;
import eu.mshade.enderframe.world.Position;
import eu.mshade.enderman.packet.play.PacketInPlayerLook;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerLookListener implements EventListener<PacketInPlayerLook> {

    @Override
    public void onEvent(PacketInPlayerLook event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publishAsync(
                new DefaultPacketLookEvent(
                        event.getYaw(), event.getPith(), event.isOnGround()),
                eventContainer);
    }

}
