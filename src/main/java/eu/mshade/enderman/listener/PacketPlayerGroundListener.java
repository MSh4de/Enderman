package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.DefaultPacketGroundEvent;
import eu.mshade.enderman.packet.play.move.PacketInPlayerGround;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerGroundListener implements EventListener<PacketInPlayerGround> {
    @Override
    public void onEvent(PacketInPlayerGround event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publishAsync(new DefaultPacketGroundEvent(event.isOnGround()), eventContainer);
    }

}
