package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsHangingMetadataRewriter;

public class BatMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public BatMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_HANGING, new IsHangingMetadataRewriter());
    }
}
