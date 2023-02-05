package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Vector;

public class MinecraftPacketOutBlockChange implements MinecraftPacketOut {

    private Vector blockPosition;
    private MaterialKey materialKey;

    public MinecraftPacketOutBlockChange(Vector blockPosition, MaterialKey materialKey) {
        this.blockPosition = blockPosition;
        this.materialKey = materialKey;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeBlockPosition(blockPosition);
        minecraftByteBuf.writeVarInt((materialKey.getId() << 4 | (materialKey.getMetadata() & 15)));
    }
}
