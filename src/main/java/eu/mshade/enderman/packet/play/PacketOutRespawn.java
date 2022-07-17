package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.world.WorldMetadataType;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.Difficulty;
import eu.mshade.enderframe.world.Dimension;
import eu.mshade.enderframe.world.LevelType;
import eu.mshade.enderframe.world.World;

public class PacketOutRespawn implements PacketOut {

    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final String levelType;

    public PacketOutRespawn(GameMode gameMode, World world) {
        this.gameMode = gameMode;
        MetadataKeyValueBucket metadataKeyValueBucket = world.getMetadataKeyValueBucket();

        Dimension dimension = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIMENSION, Dimension.class);
        Difficulty difficulty = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIFFICULTY, Difficulty.class);
        LevelType levelType = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.LEVEL_TYPE, LevelType.class);

        this.dimension = dimension;
        this.difficulty = difficulty;
        this.levelType = levelType.getName();
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeInt(dimension.getId());
        protocolBuffer.writeByte(difficulty.getId());
        protocolBuffer.writeByte(gameMode.getId());
        protocolBuffer.writeString(levelType);
    }
}
