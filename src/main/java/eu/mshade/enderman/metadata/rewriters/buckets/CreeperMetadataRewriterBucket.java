package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.CreeperStateMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsPoweredMetadataRewriter;

public class CreeperMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public CreeperMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.CREEPER_STATE, new CreeperStateMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_POWERED, new IsPoweredMetadataRewriter());
    }
}
