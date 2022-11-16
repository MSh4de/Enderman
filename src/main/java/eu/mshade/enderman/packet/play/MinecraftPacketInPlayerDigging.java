package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;
import eu.mshade.enderframe.world.Vector;
import eu.mshade.enderframe.world.block.BlockFace;
import eu.mshade.enderframe.world.block.DiggingStatus;

public class MinecraftPacketInPlayerDigging implements MinecraftPacketIn {

    private Vector blockPosition;
    private DiggingStatus diggingStatus;
    private BlockFace blockFace;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.diggingStatus = DiggingStatus.fromId(minecraftByteBuf.readByte());
        this.blockPosition = minecraftByteBuf.readBlockPosition();
        this.blockFace = BlockFace.fromId(minecraftByteBuf.readByte());
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
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

}
