package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.VillagerTypeMetadataRewriter;

public class VillagerMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public VillagerMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.VILLAGER_TYPE, new VillagerTypeMetadataRewriter());
    }
}
