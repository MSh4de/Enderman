package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.MinecraftPacketLoginEvent
import eu.mshade.enderman.packet.login.MinecraftPacketInLogin
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInLoginListener : EventListener<MinecraftPacketInLogin> {

    override fun onEvent(event: MinecraftPacketInLogin) {
        EnderFrame.get().packetEventBus.publish(MinecraftPacketLoginEvent(event.getMinecraftSession(), event.name))
    }
}