package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;
import eu.mshade.enderframe.world.block.DiggingStatus;

public class PacketInPlayerDigging implements PacketIn {

    private Vector blockPosition;
    private DiggingStatus diggingStatus;
    private BlockFace blockFace;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.diggingStatus = DiggingStatus.fromId(protocolBuffer.readByte());
        this.blockPosition = protocolBuffer.readBlockPosition();
        this.blockFace = BlockFace.fromId(protocolBuffer.readByte());
    }

    public Vector getBlockPosition() {
        return blockPosition;
    }

    public DiggingStatus getDiggingStatus() {
        return diggingStatus;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }
}
