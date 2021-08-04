package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.HealthMetadataRewriter;

public class EnderCrystalMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public EnderCrystalMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.HEALTH, new HealthMetadataRewriter());
    }
}
