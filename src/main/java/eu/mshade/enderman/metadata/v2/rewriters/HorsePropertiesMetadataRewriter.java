package eu.mshade.enderman.metadata.v2.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.Horse;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class HorsePropertiesMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        byte b = 0;
        Horse horse = (Horse) entity;

        if (horse.isTame()) b = (byte) (b | 0x02);

        if (horse.hasSaddle()) b = (byte) (b | 0x04);

        if (horse.hasChest()) b = (byte) (b | 0x08);

        if (horse.isBred()) b = (byte) (b | 0x10);

        if (horse.isEating()) b = (byte) (b | 0x20);

        if (horse.isRearing()) b = (byte) (b | 0x40);

        if (horse.mouthOpen()) b = (byte) (b | 0x80);

        metadataManager.getMetadataTypeBuffer(MetadataType.INTEGER).write(byteMessage, new IntegerMetadata(b));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.INTEGER;
    }
}
