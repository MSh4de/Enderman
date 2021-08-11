package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsChildMetadataRewriter;

public class AgeableMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public AgeableMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CHILD, new IsChildMetadataRewriter());
    }
}
