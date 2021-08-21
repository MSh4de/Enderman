package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.HealthMetadataRewriter;

public class EnderCrystalMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public EnderCrystalMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.HEALTH, new HealthMetadataRewriter());
    }
}
