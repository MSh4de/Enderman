package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.EntityMetadataBucket;
import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.type.ShortMetadata;
import eu.mshade.enderframe.metadata.type.StringMetadata;
import eu.mshade.enderman.metadata.entity.type.EntityPropertiesMetadataWrapper;


public class DefaultEntityMetadataBucket extends EntityMetadataBucket {

    public DefaultEntityMetadataBucket() {
        this.registerEntityMetadata(0, new EntityPropertiesMetadataWrapper(), EntityMetadataType.ON_FIRE, EntityMetadataType.CROUCHED, EntityMetadataType.SPRINTING, EntityMetadataType.HANDLING, EntityMetadataType.INVISIBLE);
        this.registerEntityMetadata(1, entity -> new ShortMetadata(entity.<Short>getEntityMetadata(EntityMetadataType.AIR_TICKS).get()), EntityMetadataType.AIR_TICKS);
        this.registerEntityMetadata(2, entity -> new StringMetadata(entity.<String>getEntityMetadata(EntityMetadataType.CUSTOM_NAME).get()), EntityMetadataType.CUSTOM_NAME);
        this.registerEntityMetadata(3, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.CUSTOM_NAME_VISIBLE).get()), EntityMetadataType.CUSTOM_NAME_VISIBLE);
        this.registerEntityMetadata(4, entity -> new ByteMetadata(entity.<Boolean>getEntityMetadata(EntityMetadataType.SILENT).get()), EntityMetadataType.SILENT);
    }


}
