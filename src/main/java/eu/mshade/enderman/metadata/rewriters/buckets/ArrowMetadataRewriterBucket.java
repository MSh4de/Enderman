package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsCriticalMetadataRewriter;

public class ArrowMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public ArrowMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CRITICAL, new IsCriticalMetadataRewriter());
    }
}
