package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.SkeletonTypeMetadataRewriter;

public class SkeletonMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public SkeletonMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.SKELETON_TYPE, new SkeletonTypeMetadataRewriter());
    }
}
