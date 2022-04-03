package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.EntityMetadataType;
import eu.mshade.enderframe.metadata.type.IntegerMetadata;

public class EnderCrystalEntityMetadataBucket extends EntityEntityMetadataBucket {

    public EnderCrystalEntityMetadataBucket() {
        this.registerEntityMetadata(8, entity -> new IntegerMetadata(entity.<Float>getEntityMetadata(EntityMetadataType.HEALTH).get().intValue()), EntityMetadataType.HEALTH);
        /*
        this.getMetadataRepository().registerMetadataIndex(8, MetadataType.INTEGER, EntityMetadataType.HEALTH);

         */
    }
}
