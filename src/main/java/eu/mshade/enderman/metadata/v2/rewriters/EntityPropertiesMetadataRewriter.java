package eu.mshade.enderman.metadata.v2.rewriters;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class EntityPropertiesMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        byte b = 0;

        if (entity.isFire()) b = (byte) (b | 0x01);

        if (entity.isCrounched()) b = (byte) (b | 0x02);

        if (entity.isSprinting()) b = (byte) (b | 0x04);

        if (entity.isEating()) b = (byte) (b | 0x08);

        if (entity.isInvisible()) b = (byte) (b | 0x10);

        metadataManager.getMetadataTypeBuffer(MetadataType.BYTE).write(byteMessage, new ByteMetadata(b));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.BYTE;
    }
}
