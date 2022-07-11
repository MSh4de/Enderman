package eu.mshade.enderman.metadata.entity.type;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.metadata.*;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.MetadataWrapper;
import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.type.ByteMetadata;

public class PropertiesMetadataWrapper implements MetadataWrapper<Entity> {

    @Override
    public Metadata<?> wrap(Entity entity) {
        MetadataKeyValueBucket<EntityMetadataKey> metadataKeyValueBucket = entity.getMetadataKeyValueBucket();
        int b = 0;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.ON_FIRE, OnFireEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x01;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.CROUCHED, CrouchedEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x02;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.SPRINTING, SprintingEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x08;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.HANDLING, HandlingEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x10;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.INVISIBLE, InvisibleEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x20;

        return new ByteMetadata(b);
    }

    /*
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

     */
}
