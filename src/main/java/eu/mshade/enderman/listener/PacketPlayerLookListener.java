package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketLookEvent;
import eu.mshade.enderman.packet.play.move.PacketInPlayerLook;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerLookListener implements EventListener<PacketInPlayerLook> {

    @Override
    public void onEvent(PacketInPlayerLook event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultPacketLookEvent(event.getSessionWrapper().getPlayer(), event.getYaw(), event.getPitch(), event.isOnGround()));
    }

}
