package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.OnFireMetadataRewriter;

public class BlazeMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public BlazeMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.ON_FIRE, new OnFireMetadataRewriter());
    }
}
