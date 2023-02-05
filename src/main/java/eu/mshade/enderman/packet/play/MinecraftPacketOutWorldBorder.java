package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.border.WorldBorder;
import eu.mshade.enderframe.world.border.WorldBorderAction;

public class MinecraftPacketOutWorldBorder implements MinecraftPacketOut {

    private final WorldBorderAction worldBorderAction;
    private final WorldBorder worldBorder;

    public MinecraftPacketOutWorldBorder(WorldBorderAction worldBorderAction, WorldBorder worldBorder) {
        this.worldBorderAction = worldBorderAction;
        this.worldBorder = worldBorder;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeVarInt(worldBorderAction.getAction());

        switch (worldBorderAction) {
            case SET_SIZE -> {
                minecraftByteBuf.writeDouble(worldBorder.getRadius());
            }
            case CHANGE_SIZE -> {
                minecraftByteBuf.writeDouble(worldBorder.getOldRadius());
                minecraftByteBuf.writeDouble(worldBorder.getRadius());

                if (worldBorder.getSpeed() != 0) {
                    minecraftByteBuf.writeVarLong(worldBorder.getSpeed());
                } else {
                    minecraftByteBuf.writeVarLong(0L);
                }
            }
            case SET_CENTER -> {
                minecraftByteBuf.writeDouble(worldBorder.getWorldBorderCenter().getX());
                minecraftByteBuf.writeDouble(worldBorder.getWorldBorderCenter().getZ());
            }
            case INITIALIZE -> {
                minecraftByteBuf.writeDouble(worldBorder.getWorldBorderCenter().getX());
                minecraftByteBuf.writeDouble(worldBorder.getWorldBorderCenter().getZ());

                if (worldBorder.getOldRadius() == 0) {
                    minecraftByteBuf.writeDouble(worldBorder.getRadius());
                } else {
                    minecraftByteBuf.writeDouble(worldBorder.getOldRadius());
                }
                minecraftByteBuf.writeDouble(worldBorder.getRadius());

                minecraftByteBuf.writeVarInt(worldBorder.getPortalTeleportLimit());

                minecraftByteBuf.writeVarInt(worldBorder.getWarningBlocks());
                minecraftByteBuf.writeVarInt(worldBorder.getWarningTime());

                if (worldBorder.getSpeed() != 0) {
                    minecraftByteBuf.writeVarLong(worldBorder.getSpeed());
                } else {
                    minecraftByteBuf.writeVarLong(0L);
                }
            }
            case SET_WARNING_BLOCKS -> {
                minecraftByteBuf.writeVarInt(worldBorder.getWarningBlocks());
            }
            case SET_WARNING_TIME -> {
                minecraftByteBuf.writeVarInt(worldBorder.getWarningTime());
            }
        }
    }
}
