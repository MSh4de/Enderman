package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.GameMode;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.world.*;

public class MinecraftPacketOutRespawn implements MinecraftPacketOut {

    private final GameMode gameMode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final String levelType;

    public MinecraftPacketOutRespawn(GameMode gameMode, World world) {
        this.gameMode = gameMode;
        MetadataKeyValueBucket metadataKeyValueBucket = world.getMetadataKeyValueBucket();

        Dimension dimension = (Dimension) metadataKeyValueBucket.getMetadataKeyValue(WorldMetadataType.DIMENSION).getMetadataValue();
        Difficulty difficulty = (Difficulty) metadataKeyValueBucket.getMetadataKeyValue(WorldMetadataType.DIFFICULTY).getMetadataValue();
        LevelType levelType = (LevelType) metadataKeyValueBucket.getMetadataKeyValue(WorldMetadataType.LEVEL_TYPE).getMetadataValue();

        this.dimension = dimension;
        this.difficulty = difficulty;
        this.levelType = levelType.getName();
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeInt(dimension.getId());
        minecraftByteBuf.writeByte(difficulty.getId());
        minecraftByteBuf.writeByte(gameMode.getId());
        minecraftByteBuf.writeString(levelType);
    }
}
