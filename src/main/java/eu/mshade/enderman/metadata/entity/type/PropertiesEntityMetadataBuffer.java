package eu.mshade.enderman.metadata.entity.type;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.metadata.*;
import eu.mshade.enderframe.metadata.EntityMetadataBuffer;
import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.protocol.ByteMessage;

public class PropertiesEntityMetadataBuffer implements EntityMetadataBuffer<Entity> {

    @Override
    public void write(ByteMessage byteMessage, Entity entity) {
        byte b = 0;

        if (entity.getEntityMetadataOrDefault(EntityMetadataType.ON_FIRE, OnFireEntityMetadata.DEFAULT).get()) b = (byte) (b | 0x01);

        if (entity.getEntityMetadataOrDefault(EntityMetadataType.CROUCHED, CrouchedEntityMetadata.DEFAULT).get()) b = (byte) (b | 0x02);

        if (entity.getEntityMetadataOrDefault(EntityMetadataType.SPRINTING, SprintingEntityMetadata.DEFAULT).get()) b = (byte) (b | 0x08);

        if (entity.getEntityMetadataOrDefault(EntityMetadataType.HANDLING, HandlingEntityMetadata.DEFAULT).get()) b = (byte) (b | 0x10);

        if (entity.getEntityMetadataOrDefault(EntityMetadataType.INVISIBLE, InvisibleEntityMetadata.DEFAULT).get()) b = (byte) (b | 0x20);

        byteMessage.writeByte(b);
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
