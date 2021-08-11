package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.CarriedBlockDataMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.CarriedBlockMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsScreamingMetadataRewriter;

public class EndermanMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public EndermanMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.CARRIED_BLOCK, new CarriedBlockMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.CARRIED_BLOCK_DATA, new CarriedBlockDataMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_SCREAMING, new IsScreamingMetadataRewriter());
    }
}
