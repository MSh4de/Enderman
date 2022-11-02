package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketBlockPlaceEvent;
import eu.mshade.enderman.packet.play.MinecraftPacketInBlockPlacement;
import eu.mshade.mwork.event.EventListener;

public class PacketBlockPlacementListener implements EventListener<MinecraftPacketInBlockPlacement> {

    @Override
    public void onEvent(MinecraftPacketInBlockPlacement event) {
        EnderFrame.get().getPacketEventBus().publish(new PacketBlockPlaceEvent(event.getSessionWrapper().getPlayer(), event.getBlockPosition(), event.getCursorPosition(), event.getBlockFace(), event.getItemStack()));
    }
}
