package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.RotationMetadata;
import eu.mshade.enderframe.world.Rotation;
import eu.mshade.enderman.metadata.entity.type.ArmorStandPropertiesMetadataWrapper;

public class ArmorStandMetadataBucket extends LivingEntityMetadataBucket {

    public ArmorStandMetadataBucket() {
        this.registerEntityMetadata(10, new ArmorStandPropertiesMetadataWrapper(), EntityMetadataKey.SMALL_ARMOR_STAND, EntityMetadataKey.GRAVITY, EntityMetadataKey.ARMS, EntityMetadataKey.BASE_PLATE, EntityMetadataKey.MARKER);
        this.registerEntityMetadata(11, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.HEAD_ROTATION, Rotation.class)), EntityMetadataKey.HEAD_ROTATION);
        this.registerEntityMetadata(12, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.BODY_ROTATION, Rotation.class)), EntityMetadataKey.BODY_ROTATION);
        this.registerEntityMetadata(13, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.LEFT_ARM_ROTATION, Rotation.class)), EntityMetadataKey.LEFT_ARM_ROTATION);
        this.registerEntityMetadata(14, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.RIGHT_ARM_ROTATION, Rotation.class)), EntityMetadataKey.RIGHT_ARM_ROTATION);
        this.registerEntityMetadata(15, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.LEFT_LEG_ROTATION, Rotation.class)), EntityMetadataKey.LEFT_LEG_ROTATION);
        this.registerEntityMetadata(16, entity -> new RotationMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.RIGHT_LEG_ROTATION, Rotation.class)), EntityMetadataKey.RIGHT_LEG_ROTATION);
    }

}
