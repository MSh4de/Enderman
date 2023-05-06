package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketHeldItemChangeEvent
import eu.mshade.enderman.packet.play.player.MinecraftPacketInHeldItemChange
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInHeldItemChangeListener: EventListener<MinecraftPacketInHeldItemChange> {

    override fun onEvent(event: MinecraftPacketInHeldItemChange) {
        EnderFrame.get().packetEvents.publish(MinecraftPacketHeldItemChangeEvent(event.getMinecraftSession().player, event.getSlot()))
    }
}