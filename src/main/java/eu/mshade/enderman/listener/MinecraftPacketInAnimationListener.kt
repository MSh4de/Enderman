package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.animation.AnimationType
import eu.mshade.enderframe.packetevent.MinecraftPacketAnimationEvent
import eu.mshade.enderman.packet.play.animation.MinecraftPacketInAnimation
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInAnimationListener : EventListener<MinecraftPacketInAnimation> {

    override fun onEvent(event: MinecraftPacketInAnimation) {
        EnderFrame.get().packetEvents.publish(MinecraftPacketAnimationEvent(event.getMinecraftSession().player, AnimationType.SWING_ARM))
    }
}