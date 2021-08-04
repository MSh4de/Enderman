package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.RabbitTypeMetadataRewriter;

public class RabbitMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public RabbitMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.RABBIT_TYPE, new RabbitTypeMetadataRewriter());
    }
}
