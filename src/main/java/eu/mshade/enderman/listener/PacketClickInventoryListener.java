package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.inventory.*;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.packetevent.PacketClickInventoryEvent;
import eu.mshade.enderframe.protocol.ProtocolPipeline;
import eu.mshade.enderman.packet.play.inventory.PacketInClickInventory;
import eu.mshade.enderman.wrapper.EndermanInventorySizeWrapper;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;
import io.netty.channel.Channel;


public class PacketClickInventoryListener implements EventListener<PacketInClickInventory> {

    protected EndermanInventorySizeWrapper endermanInventorySizeWrapper;

    public PacketClickInventoryListener(EndermanInventorySizeWrapper endermanInventorySizeWrapper) {
        this.endermanInventorySizeWrapper = endermanInventorySizeWrapper;
    }

    @Override
    public void onEvent(PacketInClickInventory event, ParameterContainer eventContainer) {
        Channel channel = eventContainer.getContainer(Channel.class);
        Player player = ProtocolPipeline.get().getPlayer(channel);

        ClickType clickType = ClickType.UNKNOWN;
        int button = event.getButton();
        int mode = event.getMode();
        int slot = event.getSlot();
        int key = 0;

        Inventory clickedInventory = (player.getOpenedInventory() != null ? player.getOpenedInventory() : player.getInventory());

        Integer maxSizeClickedInventory = endermanInventorySizeWrapper.wrap(clickedInventory.getInventoryKey());

        if (!(clickedInventory instanceof PlayerInventory)) {
            int maxSizeSlot = (clickedInventory instanceof ChestInventory chestInventory ? chestInventory.getRow() * 9 : maxSizeClickedInventory);
            if (slot >= 0 && slot >= maxSizeSlot) {
                clickedInventory = player.getInventory();
                int maxSizeSlotWithPlayerInventory = maxSizeSlot + (9 * 4);
                int firstSlotHotBar = maxSizeSlotWithPlayerInventory - 9;
                if (slot >= firstSlotHotBar) {
                    slot = slot - firstSlotHotBar;
                } else {
                    slot = (slot - maxSizeClickedInventory) + 9;
                }
            }

        }

        ItemStack itemStack = event.getItemStack();

        if (mode == 0) {
            if (button == 0) {
                clickType = ClickType.LEFT;
            } else if (button == 1) {
                clickType = ClickType.RIGHT;
            }
        } else if (mode == 1) {
            if (button == 0) {
                clickType = ClickType.SHIFT_LEFT;
            } else if (button == 1) {
                clickType = ClickType.SHIFT_RIGHT;
            }
        } else if (mode == 2) {
            clickType = ClickType.NUMBER_KEY;
            key = button;
        } else if (mode == 3) {
            if (button == 2) clickType = ClickType.MIDDLE;
        } else if (mode == 4) {
            if (button == 0) {
                clickType = ClickType.DROP;
            } else if (button == 1) {
                clickType = ClickType.CONTROL_DROP;
            }
        } else if (mode == 5) {
            if (button == 0) {
                clickType = ClickType.START_DRAG_LEFT;
            } else if (button == 4) {
                clickType = ClickType.START_DRAG_RIGHT;
            } else if (button == 2) {
                clickType = ClickType.END_DRAG_LEFT;
            } else if (button == 6) {
                clickType = ClickType.END_DRAG_RIGHT;
            } else if (button == 8) {
                clickType = ClickType.START_DRAG_MIDDLE;
            } else if (button == 10) {
                clickType = ClickType.END_DRAG_MIDDLE;
            } else if (button == 1 || button == 5 || button == 9) {
                clickType = ClickType.ADD_ITEM_FROM_DRAG;
            }
        } else if (mode == 6) {
            if (button == 0) clickType = ClickType.DOUBLE_CLICK;
        }


        EnderFrame.get().getPacketEventBus().publish(new PacketClickInventoryEvent(clickedInventory, clickType, itemStack, event.getId(), slot, key), eventContainer);

    }
}
