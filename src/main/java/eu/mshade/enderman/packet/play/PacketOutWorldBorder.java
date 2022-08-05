package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.border.WorldBorder;
import eu.mshade.enderframe.world.border.WorldBorderAction;

public class PacketOutWorldBorder implements PacketOut {

    private final WorldBorderAction worldBorderAction;
    private final WorldBorder worldBorder;

    public PacketOutWorldBorder(WorldBorderAction worldBorderAction, WorldBorder worldBorder) {
        this.worldBorderAction = worldBorderAction;
        this.worldBorder = worldBorder;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeVarInt(worldBorderAction.getAction());

        switch (worldBorderAction) {
            case SET_SIZE -> {
                protocolBuffer.writeDouble(worldBorder.getRadius());
            }
            case CHANGE_SIZE -> {
                protocolBuffer.writeDouble(worldBorder.getOldRadius());
                protocolBuffer.writeDouble(worldBorder.getRadius());

                if (worldBorder.getSpeed() != 0) {
                    protocolBuffer.writeVarLong(worldBorder.getSpeed());
                } else {
                    protocolBuffer.writeVarLong(0L);
                }
            }
            case SET_CENTER -> {
                protocolBuffer.writeDouble(worldBorder.getWorldBorderCenter().getX());
                protocolBuffer.writeDouble(worldBorder.getWorldBorderCenter().getZ());
            }
            case INITIALIZE -> {
                protocolBuffer.writeDouble(worldBorder.getWorldBorderCenter().getX());
                protocolBuffer.writeDouble(worldBorder.getWorldBorderCenter().getZ());

                if (worldBorder.getOldRadius() == 0) {
                    protocolBuffer.writeDouble(worldBorder.getRadius());
                } else {
                    protocolBuffer.writeDouble(worldBorder.getOldRadius());
                }
                protocolBuffer.writeDouble(worldBorder.getRadius());

                protocolBuffer.writeVarInt(worldBorder.getPortalTeleportLimit());

                protocolBuffer.writeVarInt(worldBorder.getWarningBlocks());
                protocolBuffer.writeVarInt(worldBorder.getWarningTime());

                if (worldBorder.getSpeed() != 0) {
                    protocolBuffer.writeVarLong(worldBorder.getSpeed());
                } else {
                    protocolBuffer.writeVarLong(0L);
                }
            }
            case SET_WARNING_BLOCKS -> {
                protocolBuffer.writeVarInt(worldBorder.getWarningBlocks());
            }
            case SET_WARNING_TIME -> {
                protocolBuffer.writeVarInt(worldBorder.getWarningTime());
            }
        }
    }
}
