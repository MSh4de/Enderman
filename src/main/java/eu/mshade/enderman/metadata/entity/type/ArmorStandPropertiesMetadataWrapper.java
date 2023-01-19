package eu.mshade.enderman.metadata.entity.type;

import eu.mshade.enderframe.entity.metadata.*;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.MetadataWrapper;
import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.type.ByteMetadata;

public class ArmorStandPropertiesMetadataWrapper implements MetadataWrapper<ArmorStand> {

    @Override
    public Metadata<?> wrap(ArmorStand armorStand) {
        MetadataKeyValueBucket metadataKeyValueBucket = armorStand.getMetadataKeyValueBucket();
        int b = 0;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.SMALL_ARMOR_STAND, SmallArmorStandEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x01;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.GRAVITY, GravityEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x02;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.ARMS, ArmsEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x04;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.BASE_PLATE, BasePlateEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x08;

        if (metadataKeyValueBucket.getMetadataKeyValueOrDefault(EntityMetadataKey.MARKER, MarkerEntityMetadata.DEFAULT).getMetadataValue()) b |= 0x10;

        return new ByteMetadata(b);
    }
}
