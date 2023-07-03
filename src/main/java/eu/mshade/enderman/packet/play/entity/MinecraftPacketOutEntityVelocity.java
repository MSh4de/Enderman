package eu.mshade.enderman.packet.play.entity;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.Vector;

public class MinecraftPacketOutEntityVelocity implements MinecraftPacketOut {

    private int eid;
    private Vector vector;

    public MinecraftPacketOutEntityVelocity(int eid, Vector vector) {
        this.eid = eid;
        this.vector = vector;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(eid);
        minecraftByteBuf.writeShort(((short) (vector.getX() * 8000)));
        minecraftByteBuf.writeShort(((short) (vector.getY() * 8000)));
        minecraftByteBuf.writeShort(((short) (vector.getZ() * 8000)));
    }
}
