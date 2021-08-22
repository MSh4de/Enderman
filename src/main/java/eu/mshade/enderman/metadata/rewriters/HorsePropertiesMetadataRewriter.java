package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Horse;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class HorsePropertiesMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        byte b = 0;
        Horse horse = (Horse) entity;

        if (horse.isTamed()) b = (byte) (b | 0x02);

        if (horse.isHasSaddle()) b = (byte) (b | 0x04);

        if (horse.isHasChest()) b = (byte) (b | 0x08);

        if (horse.isBred()) b = (byte) (b | 0x10);

        if (horse.isEating()) b = (byte) (b | 0x20);

        if (horse.isRearing()) b = (byte) (b | 0x40);

        if (horse.isMouthOpen()) b = (byte) (b | 0x80);

        metadataManager.getMetadataTypeBuffer(MetadataType.INTEGER).write(byteMessage, new IntegerMetadata(b));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.INTEGER;
    }
}
