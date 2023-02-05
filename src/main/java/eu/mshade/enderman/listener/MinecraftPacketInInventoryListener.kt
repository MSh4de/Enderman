package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.inventory.ClickType
import eu.mshade.enderframe.inventory.PlayerInventory
import eu.mshade.enderframe.inventory.type.ChestInventory
import eu.mshade.enderframe.packetevent.MinecraftPacketClickInventoryEvent
import eu.mshade.enderframe.packetevent.MinecraftPacketCloseInventoryEvent
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketInClickInventory
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketInCloseInventory
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketInCreativeClickInventory
import eu.mshade.enderman.wrapper.EndermanInventorySizeWrapper
import eu.mshade.mwork.event.EventListener

class MinecraftPacketInClickInventoryListener(private val endermanInventorySizeWrapper: EndermanInventorySizeWrapper): EventListener<MinecraftPacketInClickInventory> {

    override fun onEvent(event: MinecraftPacketInClickInventory) {
        val minecraftSession = event.getMinecraftSession()
        val player = minecraftSession.player

        var clickType: ClickType? = null
        val button = event.button
        val mode = event.mode
        var slot = event.slot
        var key = 0

        var clickedInventory = if (player.openedInventory != null) player.openedInventory else player.inventory

        val maxSizeClickedInventory = endermanInventorySizeWrapper.map(clickedInventory.inventoryKey)!!

        if (clickedInventory !is PlayerInventory) {
            val maxSizeSlot = if(clickedInventory is ChestInventory) clickedInventory.rows * 9 else maxSizeClickedInventory
            if (slot >= 0 && slot >= maxSizeSlot) {
                clickedInventory = player.inventory
                val maxSizeSlotWithPlayerInventory = maxSizeSlot + (9 * 4)
                val firstSlotHotBar = maxSizeSlotWithPlayerInventory - 9
                if (slot >= firstSlotHotBar) {
                    slot -= firstSlotHotBar;
                } else {
                    slot = (slot - maxSizeClickedInventory) + 9;
                }
            }
        }else {
            slot = PlayerInventory.indexFromAccurateSlot(slot)
        }

        val itemStack = event.itemStack

        clickType = when (mode) {
            0 -> if (button == 0) ClickType.LEFT else ClickType.RIGHT
            1 -> if (button == 0) ClickType.SHIFT_LEFT else ClickType.SHIFT_RIGHT
            2 -> {
                key = button
                ClickType.NUMBER_KEY
            }
            3 -> if (button == 2) ClickType.MIDDLE else null
            4 -> if (button == 0) ClickType.DROP else ClickType.CONTROL_DROP
            5 -> when (button) {
                0 -> ClickType.START_DRAG_LEFT
                4 -> ClickType.START_DRAG_RIGHT
                2 -> ClickType.END_DRAG_LEFT
                6 -> ClickType.END_DRAG_RIGHT
                8 -> ClickType.START_DRAG_MIDDLE
                10 -> ClickType.END_DRAG_MIDDLE
                1, 5, 9 -> ClickType.ADD_ITEM_FROM_DRAG
                else -> null
            }
            6 -> if (button == 0) ClickType.DOUBLE_CLICK else null
            else -> null
        }

        EnderFrame.get().packetEventBus.publish(MinecraftPacketClickInventoryEvent(player, clickedInventory, clickType, itemStack, event.id, slot, key))
    }

}

class MinecraftPacketInCloseInventoryListener: EventListener<MinecraftPacketInCloseInventory> {
    override fun onEvent(event: MinecraftPacketInCloseInventory) {
        val player = event.getMinecraftSession().player
        val inventory = if (player.openedInventory != null) player.openedInventory else player.inventory
        EnderFrame.get().packetEventBus.publish(MinecraftPacketCloseInventoryEvent(player, inventory)
        )
    }
}

class MinecraftPacketInCreativeClickInventoryListener: EventListener<MinecraftPacketInCreativeClickInventory>{

    override fun onEvent(event: MinecraftPacketInCreativeClickInventory) {
        val player = event.getMinecraftSession().player
        val slot = PlayerInventory.indexFromAccurateSlot(event.slot)
        EnderFrame.get().packetEventBus.publish(MinecraftPacketClickInventoryEvent(player, player.inventory, ClickType.CREATIVE, event.itemStack, 0, slot, 0))
    }

}

