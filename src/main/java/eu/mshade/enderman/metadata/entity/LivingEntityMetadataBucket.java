package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.FloatMetadata;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;

public class LivingEntityMetadataBucket extends DefaultEntityMetadataBucket {

    public LivingEntityMetadataBucket() {
        this.registerEntityMetadata(6, entity -> new FloatMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.HEALTH, Float.class)), EntityMetadataKey.HEALTH);
        this.registerEntityMetadata(7, entity -> new IntegerMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.POTION_EFFECT_COLOR, Integer.class)), EntityMetadataKey.POTION_EFFECT_COLOR);
        this.registerEntityMetadata(8, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.POTION_EFFECT_AMBIENT, Boolean.class)), EntityMetadataKey.POTION_EFFECT_AMBIENT);
        this.registerEntityMetadata(9, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY, Integer.class)), EntityMetadataKey.NUMBER_OF_ARROWS_IN_ENTITY);
        this.registerEntityMetadata(15, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE, Boolean.class)), EntityMetadataKey.WHETHER_ARTIFICIAL_INTELLIGENCE);
    }
}
