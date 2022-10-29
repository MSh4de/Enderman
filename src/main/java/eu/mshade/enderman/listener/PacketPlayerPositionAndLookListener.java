package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketMoveAndLookEvent;
import eu.mshade.enderman.packet.play.move.PacketInPlayerPositionAndLook;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionAndLookListener implements EventListener<PacketInPlayerPositionAndLook> {

    @Override
    public void onEvent(PacketInPlayerPositionAndLook event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultPacketMoveAndLookEvent(event.getSessionWrapper().getPlayer(), event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()));

    }

}
