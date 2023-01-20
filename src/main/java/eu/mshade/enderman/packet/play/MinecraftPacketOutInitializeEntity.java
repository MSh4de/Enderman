package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutInitializeEntity implements MinecraftPacketOut {
    private final int aInt;

    public MinecraftPacketOutInitializeEntity(int aInt) {
        this.aInt = aInt;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(aInt);
    }

}
