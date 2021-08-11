package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.v2.MetadataRewriterBucket;
import eu.mshade.enderman.metadata.rewriters.*;
import eu.mshade.enderman.metadata.v2.rewriters.*;

public class EntityMetadataRewriterBucket extends MetadataRewriterBucket {

    public EntityMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.ENTITY_PROPERTIES, new EntityPropertiesMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.AIR_TICKS, new AirTicksMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.CUSTOM_NAME, new CustomNameMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_CUSTOM_NAME_VISIBLE, new CustomNameVisibleMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_SILENT, new SilentMetadataRewriter());
    }
}
