package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.BlockPosition;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class PacketInBlockPlacement implements PacketIn {



    private Vector blockPosition;
    private ItemStack itemStack;
    private BlockFace blockFace;


    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.blockPosition = protocolBuffer.readBlockPosition();

        blockFace = BlockFace.fromId(protocolBuffer.readByte());


        this.itemStack = protocolBuffer.readItemStack();

        int cursorX = protocolBuffer.readByte();
        int cursorY = protocolBuffer.readByte();
        int cursorZ = protocolBuffer.readByte();


        /*
        System.out.println(face);
        System.out.println(type);
        System.out.println(amount);
        System.out.println(durability);
        System.out.println(compoundBinaryTag);

         */

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

    @Override
    public String toString() {
        return "PacketInBlockPlacement{" +
                "blockPosition=" + blockPosition +
                ", itemStack=" + itemStack +
                ", blockFace=" + blockFace +
                '}';
    }
}
