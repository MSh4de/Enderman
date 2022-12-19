package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketClientStatusEvent
import eu.mshade.enderman.packet.play.player.MinecraftPacketInClientStatus
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInClientStatusListener: EventListener<MinecraftPacketInClientStatus> {
    override fun onEvent(event: MinecraftPacketInClientStatus) {
        EnderFrame.get().packetEventBus.publish(MinecraftPacketClientStatusEvent(event.getMinecraftSession().player, event.clientStatus))
    }
}