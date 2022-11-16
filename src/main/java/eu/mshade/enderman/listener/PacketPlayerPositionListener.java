package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketMoveEvent;
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPosition;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionListener implements EventListener<MinecraftPacketInPlayerPosition> {

    @Override
    public void onEvent(MinecraftPacketInPlayerPosition event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultMinecraftPacketMoveEvent(event.getSessionWrapper().getPlayer(), event.getX(), event.getY(), event.getZ(), event.isOnGround()));
    }

}
