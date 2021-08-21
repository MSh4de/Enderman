package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;

public class BoatMetadataBuffer extends EntityMetadataBuffer {

    public BoatMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(17, MetadataType.INTEGER, MetadataMeaning.TIME_SINCE_LAST_HIT);
        this.getMetadataRepository().registerMetadataIndex(18, MetadataType.INTEGER, MetadataMeaning.FORWARD_DIRECTION);
        this.getMetadataRepository().registerMetadataIndex(19, MetadataType.FLOAT, MetadataMeaning.DAMAGE_TAKEN);
    }
}
