package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketTabCompleteEvent
import eu.mshade.enderman.packet.play.chat.MinecraftPacketInTabComplete
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInTabCompleteListener : EventListener<MinecraftPacketInTabComplete> {
    override fun onEvent(event: MinecraftPacketInTabComplete) {
        EnderFrame.get().packetEvents.publish(
            MinecraftPacketTabCompleteEvent(
                event.getMinecraftSession(),
                event.text
            )
        )
    }
}