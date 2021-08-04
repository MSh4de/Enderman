package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.IsChildMetadataRewriter;

public class AgeableMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public AgeableMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CHILD, new IsChildMetadataRewriter());
    }
}
