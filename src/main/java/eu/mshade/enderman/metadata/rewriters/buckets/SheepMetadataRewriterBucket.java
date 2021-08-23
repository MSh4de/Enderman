package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.SheepPropertiesMetadataRewriter;

public class SheepMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public SheepMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.SHEEP_PROPERTIES, new SheepPropertiesMetadataRewriter());
    }
}