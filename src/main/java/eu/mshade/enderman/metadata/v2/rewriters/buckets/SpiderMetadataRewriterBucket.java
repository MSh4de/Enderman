package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.IsClimbingMetadataRewriter;

public class SpiderMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public SpiderMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.CLIMBING, new IsClimbingMetadataRewriter());
    }
}
