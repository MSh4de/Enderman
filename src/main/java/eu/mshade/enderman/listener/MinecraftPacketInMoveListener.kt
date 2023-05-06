package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketGroundEvent
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketLookEvent
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketMoveAndLookEvent
import eu.mshade.enderframe.packetevent.DefaultMinecraftPacketMoveEvent
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerGround
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerLook
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPosition
import eu.mshade.enderman.packet.play.move.MinecraftPacketInPlayerPositionAndLook
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInPlayerGroundListener :
    EventListener<MinecraftPacketInPlayerGround> {
    override fun onEvent(event: MinecraftPacketInPlayerGround) {
        EnderFrame.get().packetEvents.publishAsync(
            DefaultMinecraftPacketGroundEvent(
                event.getMinecraftSession().player,
                event.isOnGround
            )
        )
    }
}

class MinecraftPacketInPlayerLookListener :
    EventListener<MinecraftPacketInPlayerLook> {
    override fun onEvent(event: MinecraftPacketInPlayerLook) {
        EnderFrame.get().packetEvents.publishAsync(
            DefaultMinecraftPacketLookEvent(
                event.getMinecraftSession().player,
                event.yaw,
                event.pitch,
                event.isOnGround
            )
        )
    }
}

class MinecraftPacketInPlayerPositionAndLookListener :
    EventListener<MinecraftPacketInPlayerPositionAndLook> {
    override fun onEvent(event: MinecraftPacketInPlayerPositionAndLook) {
        EnderFrame.get().packetEvents.publishAsync(
            DefaultMinecraftPacketMoveAndLookEvent(
                event.getMinecraftSession().player,
                event.x,
                event.y,
                event.z,
                event.yaw,
                event.pitch,
                event.isOnGround
            )
        )
    }
}

class MinecraftPacketInPlayerPositionListener :
    EventListener<MinecraftPacketInPlayerPosition> {
    override fun onEvent(event: MinecraftPacketInPlayerPosition) {
        EnderFrame.get().packetEvents.publishAsync(
            DefaultMinecraftPacketMoveEvent(
                event.getMinecraftSession().player,
                event.x,
                event.y,
                event.z,
                event.isOnGround
            )
        )
    }
}