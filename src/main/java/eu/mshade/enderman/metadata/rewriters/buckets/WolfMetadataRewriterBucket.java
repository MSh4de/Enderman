package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.HealthMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsBeggingMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsCollarColorMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.WolfPropertiesMetadataRewriter;

public class WolfMetadataRewriterBucket extends TameableMetadataRewriterBucket {

    public WolfMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.WOLF_PROPERTIES, new WolfPropertiesMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HEALTH, new HealthMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_BEGGING, new IsBeggingMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.COLLAR_COLOR, new IsCollarColorMetadataRewriter());
    }
}
