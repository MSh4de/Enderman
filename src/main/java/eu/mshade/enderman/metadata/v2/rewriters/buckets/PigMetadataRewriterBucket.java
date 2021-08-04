package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.HasSaddleMetadataRewriter;

public class PigMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public PigMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.HAS_SADDLE, new HasSaddleMetadataRewriter());
    }
}
