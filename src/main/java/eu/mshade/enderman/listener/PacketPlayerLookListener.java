package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketLookEvent;
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerLook;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerLookListener implements EventListener<MinecraftPacketInPlayerLook> {

    @Override
    public void onEvent(MinecraftPacketInPlayerLook event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultMinecraftPacketLookEvent(event.getSessionWrapper().getPlayer(), event.getYaw(), event.getPitch(), event.isOnGround()));
    }

}
