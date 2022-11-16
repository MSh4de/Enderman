package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketLoginEvent;
import eu.mshade.enderman.packet.login.MinecraftPacketInLogin;
import eu.mshade.mwork.event.EventListener;

public class PacketLoginListener implements EventListener<MinecraftPacketInLogin> {

    @Override
    public void onEvent(MinecraftPacketInLogin event) {
        EnderFrame.get().getPacketEventBus().publish(new MinecraftPacketLoginEvent(event.getSessionWrapper(), event.getName()));
    }

}
