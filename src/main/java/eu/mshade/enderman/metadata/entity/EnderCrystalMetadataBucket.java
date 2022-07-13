package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;

public class EnderCrystalMetadataBucket extends DefaultEntityMetadataBucket {

    public EnderCrystalMetadataBucket() {
        this.registerEntityMetadata(8, entity -> new IntegerMetadata(entity.getMetadataKeyValueBucket().getValueOfMetadataKeyValue(EntityMetadataKey.HEALTH, Float.class).intValue()), EntityMetadataKey.HEALTH);
        /*
        this.getMetadataRepository().registerMetadataIndex(8, MetadataType.INTEGER, EntityMetadataType.HEALTH);

         */
    }
}
