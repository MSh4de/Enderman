package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.OnFireMetadataRewriter;

public class BlazeMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public BlazeMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.ON_FIRE, new OnFireMetadataRewriter());
    }
}
