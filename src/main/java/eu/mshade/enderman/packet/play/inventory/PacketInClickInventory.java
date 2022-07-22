package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.inventory.ClickType;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInClickInventory implements PacketIn {

    private int id, slot, key;
    private short transactionId;
    private ClickType clickType = ClickType.UNKNOWN;
    private ItemStack itemStack;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        id = protocolBuffer.readByte();
        slot = protocolBuffer.readShort();

        byte button = protocolBuffer.readByte();
        transactionId = protocolBuffer.readShort();
        byte mode = protocolBuffer.readByte();

        itemStack = protocolBuffer.readItemStack();

        if (mode == 0){
            if (button == 0){
                this.clickType = ClickType.LEFT;
            }else if (button == 1){
                this.clickType = ClickType.RIGHT;
            }
        } else if (mode == 1){
            if (button == 0){
                this.clickType = ClickType.SHIFT_LEFT;
            }else if (button == 1){
                this.clickType = ClickType.SHIFT_RIGHT;
            }
        }else if (mode == 2){
            this.clickType = ClickType.NUMBER_KEY;
            this.key = button;
        } else if (mode == 3) {
            if (button == 2) this.clickType = ClickType.MIDDLE;
        } else if (mode == 4) {
            //need to check inventory server side from slot
            if (itemStack.getMaterial().equals(Material.AIR)) {
                if (button == 0) {
                    this.clickType = ClickType.WINDOW_BORDER_LEFT;
                } else if (button == 1) {
                    this.clickType = ClickType.WINDOW_BORDER_RIGHT;
                }
            } else {
                if (button == 0) {
                    this.clickType = ClickType.DROP;
                } else if (button == 1) {
                    this.clickType = ClickType.CONTROL_DROP;
                }
            }
        } else if (mode == 5) {
            if (button == 0){
                this.clickType = ClickType.START_DRAG_LEFT;
            } else if (button == 4) {
                this.clickType = ClickType.START_DRAG_RIGHT;
            } else if (button == 2) {
                this.clickType = ClickType.END_DRAG_LEFT;
            } else if (button == 6) {
                this.clickType = ClickType.END_DRAG_RIGHT;
            }else if (button == 1 || button == 5){
                this.clickType = ClickType.ADD_ITEM_FROM_DRAG;
            }
        } else if (mode == 6) {
            if (button == 0) this.clickType = ClickType.DOUBLE_CLICK;
        }
    }

    public int getId() {
        return id;
    }

    public int getSlot() {
        return slot;
    }

    public int getKey() {
        return key;
    }

    public short getTransactionId() {
        return transactionId;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
