package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketBlockPlaceEvent;
import eu.mshade.enderframe.world.block.BlockFace;
import eu.mshade.enderman.packet.play.PacketInBlockPlacement;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketBlockPlacementListener implements EventListener<PacketInBlockPlacement> {

    @Override
    public void onEvent(PacketInBlockPlacement event) {
        EnderFrame.get().getPacketEventBus().publish(new PacketBlockPlaceEvent(event.getSessionWrapper().getPlayer(), event.getBlockPosition(), event.getCursorPosition(), event.getBlockFace(), event.getItemStack()));
    }
}
