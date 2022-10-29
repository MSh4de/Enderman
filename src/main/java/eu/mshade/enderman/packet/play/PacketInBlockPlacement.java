package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;

public class PacketInBlockPlacement implements PacketIn {


    private Vector blockPosition;
    private ItemStack itemStack;
    private BlockFace blockFace;
    private Vector cursorPosition;
    private SessionWrapper sessionWrapper;


    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.blockPosition = protocolBuffer.readBlockPosition();

        blockFace = BlockFace.fromId(protocolBuffer.readByte());

        try {
            this.itemStack = protocolBuffer.readItemStack();

        }catch (Exception e){
            e.printStackTrace();
        }

        int cursorX = protocolBuffer.readByte();
        int cursorY = protocolBuffer.readByte();
        int cursorZ = protocolBuffer.readByte();

        this.cursorPosition = new Vector(cursorX, cursorY, cursorZ);

    }

    public Vector getBlockPosition() {
        return blockPosition;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public Vector getCursorPosition() {
        return cursorPosition;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    @Override
    public String toString() {
        return "PacketInBlockPlacement{" +
                "blockPosition=" + blockPosition +
                ", itemStack=" + itemStack +
                ", blockFace=" + blockFace +
                ", cursorPosition=" + cursorPosition +
                '}';
    }
}
