package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketBlockPlaceEvent
import eu.mshade.enderframe.packetevent.MinecraftPacketPlayerDiggingEvent
import eu.mshade.enderman.packet.play.MinecraftPacketInBlockPlacement
import eu.mshade.enderman.packet.play.MinecraftPacketInPlayerDigging
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInBlockPlacementListener : EventListener<MinecraftPacketInBlockPlacement> {

    override fun onEvent(event: MinecraftPacketInBlockPlacement) {

        EnderFrame.get().packetEvents.publish(
            MinecraftPacketBlockPlaceEvent(
                event.getMinecraftSession().player,
                event.blockPosition,
                event.cursorPosition,
                event.blockFace,
                event.itemStack
            )
        )
    }
}

class MinecraftPacketInPlayerDiggingListener : EventListener<MinecraftPacketInPlayerDigging> {

    override fun onEvent(event: MinecraftPacketInPlayerDigging) {
        val player = event.getMinecraftSession().player

        EnderFrame.get().packetEvents.publish(
            MinecraftPacketPlayerDiggingEvent(
                player,
                event.blockPosition,
                event.blockFace,
                event.diggingStatus
            )
        )
    }
}