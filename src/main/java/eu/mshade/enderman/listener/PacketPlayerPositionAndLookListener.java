package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketMoveEvent;
import eu.mshade.enderframe.event.entity.PacketMoveType;
import eu.mshade.enderframe.world.Position;
import eu.mshade.enderman.packet.play.PacketInPlayerPositionAndLook;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionAndLookListener implements EventListener<PacketInPlayerPositionAndLook> {

    @Override
    public void onEvent(PacketInPlayerPositionAndLook event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(
                new PacketMoveEvent(
                        new Position(event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPith(), event.isOnGround()),
                        PacketMoveType.POSITION_AND_LOOK),
                eventContainer);
    }

}
