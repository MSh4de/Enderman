package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketGroundEvent;
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerGround;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerGroundListener implements EventListener<MinecraftPacketInPlayerGround> {
    @Override
    public void onEvent(MinecraftPacketInPlayerGround event) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultPacketGroundEvent(event.getSessionWrapper().getPlayer(), event.isOnGround()));
    }

}
