package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.IsAttackingMetadataRewriter;

public class GhastMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public GhastMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_ATTACKING, new IsAttackingMetadataRewriter());
    }
}
