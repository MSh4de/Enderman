package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketMoveAndLookEvent;
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPositionAndLook;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionAndLookListener implements EventListener<MinecraftPacketInPlayerPositionAndLook> {

    @Override
    public void onEvent(MinecraftPacketInPlayerPositionAndLook event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultPacketMoveAndLookEvent(event.getSessionWrapper().getPlayer(), event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()));

    }

}
