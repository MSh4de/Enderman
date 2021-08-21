package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.GuardianPropertiesMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.TargetEntityIdMetadataRewriter;

public class GuardianMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public GuardianMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.GUARDIAN_PROPERTIES, new GuardianPropertiesMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.TARGET_ENTITY_ID, new TargetEntityIdMetadataRewriter());
    }
}
