package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketToggleFlyingEvent
import eu.mshade.enderman.packet.play.MinecraftPacketInPlayerAbilities
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInPlayerAbilitiesListener: EventListener<MinecraftPacketInPlayerAbilities> {
    override fun onEvent(event: MinecraftPacketInPlayerAbilities) {
        val player = event.getMinecraftSession().player
        EnderFrame.get().packetEvents.publish(
            MinecraftPacketToggleFlyingEvent(
                player,
                event.isAllowFlying
            )
        )
    }
}