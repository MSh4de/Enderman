package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsPoweredMetadataRewriter;

public class FurnaceMinecartMetadataRewriterBucket extends MinecartMetadataRewriterBucket {

    public FurnaceMinecartMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_POWERED, new IsPoweredMetadataRewriter());
    }
}
