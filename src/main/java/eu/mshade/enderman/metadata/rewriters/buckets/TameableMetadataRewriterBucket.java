package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.OwnerMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.TameablePropertiesRewriter;

public class TameableMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public TameableMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.TAMEABLE_PROPERTIES, new TameablePropertiesRewriter());
        registerMetadataRewriter(MetadataMeaning.OWNER, new OwnerMetadataRewriter());
    }
}
