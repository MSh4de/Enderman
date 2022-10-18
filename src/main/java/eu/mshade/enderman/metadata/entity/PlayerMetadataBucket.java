package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;
import eu.mshade.enderframe.mojang.SkinPart;

public class PlayerMetadataBucket extends LivingEntityMetadataBucket {

    public PlayerMetadataBucket() {
        this.registerEntityMetadata(10, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.SKIN_PART, SkinPart.class).toByte()), EntityMetadataKey.SKIN_PART);
        this.registerEntityMetadata(17, entity -> new FloatMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.ADDITIONAL_HEARTS, Float.class)), EntityMetadataKey.ADDITIONAL_HEARTS);
        this.registerEntityMetadata(18, entity -> new IntegerMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.SCORE, Integer.class)), EntityMetadataKey.SCORE);
    }
}
