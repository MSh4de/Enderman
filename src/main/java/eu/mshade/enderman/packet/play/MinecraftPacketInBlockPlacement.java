package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;

public class MinecraftPacketInBlockPlacement implements MinecraftPacketIn {


    private Vector blockPosition;
    private ItemStack itemStack;
    private BlockFace blockFace;
    private Vector cursorPosition;
    private MinecraftSession minecraftSession;


    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.blockPosition = minecraftByteBuf.readBlockPosition();

        blockFace = BlockFace.fromId(minecraftByteBuf.readByte());

        try {
            this.itemStack = minecraftByteBuf.readItemStack();

        }catch (Exception e){
            e.printStackTrace();
        }

        int cursorX = minecraftByteBuf.readByte();
        int cursorY = minecraftByteBuf.readByte();
        int cursorZ = minecraftByteBuf.readByte();

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
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
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
