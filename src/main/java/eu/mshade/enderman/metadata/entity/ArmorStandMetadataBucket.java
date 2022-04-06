package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.entity.metadata.HeadRotationEntityMetadata;
import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.world.Rotation;
import eu.mshade.enderman.metadata.entity.type.ArmorStandPropertiesMetadataWrapper;

public class ArmorStandMetadataBucket extends LivingEntityMetadataBucket {

    public ArmorStandMetadataBucket() {
        this.registerEntityMetadata(10, new ArmorStandPropertiesMetadataWrapper(), EntityMetadataType.SMALL_ARMOR_STAND, EntityMetadataType.GRAVITY, EntityMetadataType.ARMS, EntityMetadataType.BASE_PLATE, EntityMetadataType.MARKER);
        this.registerEntityMetadata(11, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.HEAD_ROTATION).get()), EntityMetadataType.HEAD_ROTATION);
        this.registerEntityMetadata(12, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.BODY_ROTATION).get()), EntityMetadataType.BODY_ROTATION);
        this.registerEntityMetadata(13, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.LEFT_ARM_ROTATION).get()), EntityMetadataType.LEFT_ARM_ROTATION);
        this.registerEntityMetadata(14, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.RIGHT_ARM_ROTATION).get()), EntityMetadataType.RIGHT_ARM_ROTATION);
        this.registerEntityMetadata(15, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.LEFT_LEG_ROTATION).get()), EntityMetadataType.LEFT_LEG_ROTATION);
        this.registerEntityMetadata(16, entity -> new RotationMetadata(entity.<Rotation>getEntityMetadata(EntityMetadataType.RIGHT_LEG_ROTATION).get()), EntityMetadataType.RIGHT_LEG_ROTATION);
    }

}
