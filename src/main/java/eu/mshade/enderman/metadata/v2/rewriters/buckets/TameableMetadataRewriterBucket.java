package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.OwnerMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.TameablePropertiesRewriter;

public class TameableMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public TameableMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.TAMEABLE_PROPERTIES, new TameablePropertiesRewriter());
        registerMetadataRewriter(MetadataMeaning.OWNER, new OwnerMetadataRewriter());
    }
}
