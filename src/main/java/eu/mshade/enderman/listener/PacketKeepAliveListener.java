package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketKeepAliveEvent;
import eu.mshade.enderman.packet.play.PacketInKeepAlive;
import eu.mshade.mwork.event.EventContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketKeepAliveListener implements EventListener<PacketInKeepAlive> {

    @Override
    public void onEvent(PacketInKeepAlive event, EventContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketKeepAliveEvent(event.getThreshold()), eventContainer);
    }
}
