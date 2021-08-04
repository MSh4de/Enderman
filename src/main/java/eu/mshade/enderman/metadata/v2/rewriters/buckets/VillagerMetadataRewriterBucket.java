package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.VillagerTypeMetadataRewriter;

public class VillagerMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public VillagerMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.VILLAGER_TYPE, new VillagerTypeMetadataRewriter());
    }
}
