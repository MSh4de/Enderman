package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.InvulnerableTimeMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.WatchedTargetMetadataRewriter;

public class WitherMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public WitherMetadataRewriterBucket(int index) {
        registerMetadataRewriter(MetadataMeaning.WATCHED_TARGET, new WatchedTargetMetadataRewriter(index));
        registerMetadataRewriter(MetadataMeaning.INVULNERABLE_TIME, new InvulnerableTimeMetadataRewriter());
    }
}
