package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.world.BlockPosition;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

public class PacketInPlayerBlockPlacement extends PacketIn {



    private BlockPosition blockPosition;
    private byte face;


    @Override
    public void deserialize(ByteMessage byteMessage) {
        BlockPosition blockPosition = byteMessage.readBlockPosition();

        byte face = byteMessage.readByte();

        short type = byteMessage.readShort();
        int amount = byteMessage.readUnsignedByte();
        short durability = byteMessage.readShort();
        CompoundBinaryTag compoundBinaryTag = byteMessage.readCompoundTag();

        int cursorX = byteMessage.readByte();
        int cursorY = byteMessage.readByte();
        int cursorZ = byteMessage.readByte();

        System.out.println(face);
        System.out.println(type);
        System.out.println(amount);
        System.out.println(durability);
        System.out.println(compoundBinaryTag);

    }
}
