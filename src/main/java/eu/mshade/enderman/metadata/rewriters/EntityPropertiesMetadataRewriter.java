package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class EntityPropertiesMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        byte b = 0;

        if (entity.isFire()) b = (byte) (b | 0x01);

        if (entity.isSneaking()) b = (byte) (b | 0x02);

        if (entity.isSprinting()) b = (byte) (b | 0x08);

        if (entity.isEating()) b = (byte) (b | 0x10);

        if (entity.isInvisible()) b = (byte) (b | 0x20);

        metadataManager.getMetadataTypeBuffer(MetadataType.BYTE).write(byteMessage, new ByteMetadata(b));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.BYTE;
    }
}
