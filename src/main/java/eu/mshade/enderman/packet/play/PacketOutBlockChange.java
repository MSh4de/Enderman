package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Vector;

public class PacketOutBlockChange implements PacketOut {

    private Vector blockPosition;
    private MaterialKey materialKey;

    public PacketOutBlockChange(Vector blockPosition, MaterialKey materialKey) {
        this.blockPosition = blockPosition;
        this.materialKey = materialKey;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeBlockPosition(blockPosition);
        protocolBuffer.writeVarInt((materialKey.getId() << 4 | (materialKey.getData() & 15)));
    }
}
