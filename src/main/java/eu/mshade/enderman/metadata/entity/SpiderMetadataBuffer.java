package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.buffer.MetadataBuffer;

public class SpiderMetadataBuffer extends LivingEntityMetadataBuffer {

    public SpiderMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(16, MetadataType.BYTE, MetadataMeaning.CLIMBING);
    }
}
