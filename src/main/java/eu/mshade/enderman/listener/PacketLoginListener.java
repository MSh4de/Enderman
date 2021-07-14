package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketLoginEvent;
import eu.mshade.enderman.packet.login.PacketInLogin;
import eu.mshade.mwork.event.EventContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketLoginListener implements EventListener<PacketInLogin> {

    @Override
    public void onEvent(PacketInLogin event, EventContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketLoginEvent(event.getName()), eventContainer);
    }

}
