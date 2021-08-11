package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.OcelotTypeRewriter;

public class OcelotMetadataRewriterBucket extends TameableMetadataRewriterBucket {

    public OcelotMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.OCELOT_TYPE, new OcelotTypeRewriter());
    }
}
