package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.CreeperStateMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.IsPoweredMetadataRewriter;

public class CreeperMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public CreeperMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.CREEPER_STATE, new CreeperStateMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_POWERED, new IsPoweredMetadataRewriter());
    }
}
