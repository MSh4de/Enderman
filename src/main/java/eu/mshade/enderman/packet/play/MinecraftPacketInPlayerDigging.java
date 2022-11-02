package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;
import eu.mshade.enderframe.world.block.DiggingStatus;

public class MinecraftPacketInPlayerDigging implements MinecraftPacketIn {

    private Vector blockPosition;
    private DiggingStatus diggingStatus;
    private BlockFace blockFace;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
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

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

}
