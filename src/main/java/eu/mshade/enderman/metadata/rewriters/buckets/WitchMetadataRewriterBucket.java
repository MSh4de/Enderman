package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsAgressiveMetadataRewriter;

public class WitchMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public WitchMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_AGRESSIVE, new IsAgressiveMetadataRewriter());
    }
}
