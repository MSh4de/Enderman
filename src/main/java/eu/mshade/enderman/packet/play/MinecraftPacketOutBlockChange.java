package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Vector;

public class MinecraftPacketOutBlockChange implements MinecraftPacketOut {

    private Vector blockPosition;
    private MaterialKey materialKey;

    public MinecraftPacketOutBlockChange(Vector blockPosition, MaterialKey materialKey) {
        this.blockPosition = blockPosition;
        this.materialKey = materialKey;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeBlockPosition(blockPosition);
        protocolBuffer.writeVarInt((materialKey.getId() << 4 | (materialKey.getMetadata() & 15)));
    }
}
