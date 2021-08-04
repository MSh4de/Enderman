package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.OcelotTypeRewriter;

public class OcelotMetadataRewriterBucket extends TameableMetadataRewriterBucket {

    public OcelotMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.OCELOT_TYPE, new OcelotTypeRewriter());
    }
}
