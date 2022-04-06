package eu.mshade.enderman.metadata.entity.type;

import eu.mshade.enderframe.entity.ArmorStand;
import eu.mshade.enderframe.entity.metadata.*;
import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.EntityMetadataWrapper;
import eu.mshade.enderframe.metadata.Metadata;
import eu.mshade.enderframe.metadata.type.ByteMetadata;

public class ArmorStandPropertiesMetadataWrapper implements EntityMetadataWrapper<ArmorStand> {

    @Override
    public Metadata<?> wrap(ArmorStand armorStand) {
        int b = 0;
        if (armorStand.getEntityMetadataOrDefault(EntityMetadataType.SMALL_ARMOR_STAND, SmallArmorStandEntityMetadata.DEFAULT).get()) b = b | 0x01;

        if (armorStand.getEntityMetadataOrDefault(EntityMetadataType.GRAVITY, GravityEntityMetadata.DEFAULT).get()) b = b | 0x02;

        if (armorStand.getEntityMetadataOrDefault(EntityMetadataType.ARMS, ArmsEntityMetadata.DEFAULT).get()) b = b | 0x04;

        if (armorStand.getEntityMetadataOrDefault(EntityMetadataType.BASE_PLATE, BasePlateEntityMetadata.DEFAULT).get()) b = b | 0x08;

        if (armorStand.getEntityMetadataOrDefault(EntityMetadataType.MARKER, MarkerEntityMetadata.DEFAULT).get()) b = b | 0x10;

        return new ByteMetadata(b);
    }
}
