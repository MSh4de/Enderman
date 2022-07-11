package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.ShortMetadata;
import eu.mshade.enderframe.metadata.type.StringMetadata;
import eu.mshade.enderman.metadata.entity.type.PropertiesMetadataWrapper;


public class DefaultEntityMetadataBucket extends EntityMetadataBucket {

    public DefaultEntityMetadataBucket() {
        this.registerEntityMetadata(0, new PropertiesMetadataWrapper(), EntityMetadataKey.ON_FIRE, EntityMetadataKey.CROUCHED, EntityMetadataKey.SPRINTING, EntityMetadataKey.HANDLING, EntityMetadataKey.INVISIBLE);
        this.registerEntityMetadata(1, entity -> new ShortMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.AIR_TICKS, Short.class)), EntityMetadataKey.AIR_TICKS);
        this.registerEntityMetadata(2, entity -> new StringMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.CUSTOM_NAME, String.class)), EntityMetadataKey.CUSTOM_NAME);
        this.registerEntityMetadata(3, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.CUSTOM_NAME_VISIBLE, Boolean.class)), EntityMetadataKey.CUSTOM_NAME_VISIBLE);
        this.registerEntityMetadata(4, entity -> new ByteMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.SILENT, Boolean.class)), EntityMetadataKey.SILENT);
    }


}
