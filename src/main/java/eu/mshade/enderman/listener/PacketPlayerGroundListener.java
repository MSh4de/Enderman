package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.event.entity.PacketMoveEvent;
import eu.mshade.enderframe.event.entity.PacketMoveType;
import eu.mshade.enderframe.world.Position;
import eu.mshade.enderman.packet.play.PacketInPlayerGround;
import eu.mshade.mwork.event.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketPlayerGroundListener implements EventListener<PacketInPlayerGround> {
    @Override
    public void onEvent(PacketInPlayerGround event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(
                new PacketMoveEvent(
                        new Position(event.isOnGround()), PacketMoveType.GROUND),
                eventContainer);
    }

}
