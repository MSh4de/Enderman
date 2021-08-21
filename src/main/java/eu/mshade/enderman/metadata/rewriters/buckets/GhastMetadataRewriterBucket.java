package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsAttackingMetadataRewriter;

public class GhastMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public GhastMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_ATTACKING, new IsAttackingMetadataRewriter());
    }
}
