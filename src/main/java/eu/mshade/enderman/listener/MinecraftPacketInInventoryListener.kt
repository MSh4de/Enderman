package eu.mshade.enderman.listener

import eu.mshade.enderframe.EnderFrame
import eu.mshade.enderframe.inventory.ChestInventory
import eu.mshade.enderframe.inventory.ClickType
import eu.mshade.enderframe.inventory.PlayerInventory
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

        var clickType = ClickType.UNKNOWN
        val button = event.button
        val mode = event.mode
        var slot = event.slot
        var key = 0

        var clickedInventory = if (player.openedInventory != null) player.openedInventory else player.inventory

        val maxSizeClickedInventory = endermanInventorySizeWrapper.wrap(clickedInventory.inventoryKey)!!

        if (clickedInventory !is PlayerInventory) {
            val maxSizeSlot = if(clickedInventory is ChestInventory) clickedInventory.getRow() * 9 else maxSizeClickedInventory
            if (slot >= 0 && slot >= maxSizeSlot) {
                clickedInventory = player.getInventory()
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

        if (mode == 0) {
            if (button == 0) {
                clickType = ClickType.LEFT
            } else if (button == 1) {
                clickType = ClickType.RIGHT
            }
        } else if (mode == 1) {
            if (button == 0) {
                clickType = ClickType.SHIFT_LEFT
            } else if (button == 1) {
                clickType = ClickType.SHIFT_RIGHT
            }
        } else if (mode == 2) {
            clickType = ClickType.NUMBER_KEY
            key = button
        } else if (mode == 3) {
            if (button == 2) clickType = ClickType.MIDDLE
        } else if (mode == 4) {
            if (button == 0) {
                clickType = ClickType.DROP
            } else if (button == 1) {
                clickType = ClickType.CONTROL_DROP
            }
        } else if (mode == 5) {
            if (button == 0) {
                clickType = ClickType.START_DRAG_LEFT
            } else if (button == 4) {
                clickType = ClickType.START_DRAG_RIGHT
            } else if (button == 2) {
                clickType = ClickType.END_DRAG_LEFT
            } else if (button == 6) {
                clickType = ClickType.END_DRAG_RIGHT
            } else if (button == 8) {
                clickType = ClickType.START_DRAG_MIDDLE
            } else if (button == 10) {
                clickType = ClickType.END_DRAG_MIDDLE
            } else if (button == 1 || button == 5 || button == 9) {
                clickType = ClickType.ADD_ITEM_FROM_DRAG
            }
        } else if (mode == 6) {
            if (button == 0) clickType = ClickType.DOUBLE_CLICK
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

