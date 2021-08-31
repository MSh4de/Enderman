package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.packetevent.PacketLoginEvent;
import eu.mshade.enderman.packet.login.PacketInLogin;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketLoginListener implements EventListener<PacketInLogin> {

    @Override
    public void onEvent(PacketInLogin event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketLoginEvent(eventContainer.getContainer(EnderFrameSessionHandler.class).getEnderFrameSession().getPlayer(),
                event.getName()), eventContainer);
    }

}
