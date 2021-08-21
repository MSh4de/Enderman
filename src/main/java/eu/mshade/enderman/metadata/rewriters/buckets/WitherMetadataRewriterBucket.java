package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.InvulnerableTimeMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.WatchedTargetMetadataRewriter;

public class WitherMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public WitherMetadataRewriterBucket(int index) {
        registerMetadataRewriter(MetadataMeaning.WATCHED_TARGET, new WatchedTargetMetadataRewriter(index));
        registerMetadataRewriter(MetadataMeaning.INVULNERABLE_TIME, new InvulnerableTimeMetadataRewriter());
    }
}
