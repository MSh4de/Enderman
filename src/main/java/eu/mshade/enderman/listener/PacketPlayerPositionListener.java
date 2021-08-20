package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketMoveEvent;
import eu.mshade.enderframe.event.entity.PacketMoveType;
import eu.mshade.enderframe.world.Position;
import eu.mshade.enderman.packet.play.PacketInPlayerPosition;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerPositionListener implements EventListener<PacketInPlayerPosition> {

    @Override
    public void onEvent(PacketInPlayerPosition event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(
                new PacketMoveEvent(
                        new Position(event.getX(), event.getY(), event.getZ(), event.isOnGround()),
                        PacketMoveType.POSITION),
                eventContainer);
    }

}
