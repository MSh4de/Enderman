package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.IsCriticalMetadataRewriter;

public class ArrowMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public ArrowMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CRITICAL, new IsCriticalMetadataRewriter());
    }
}
