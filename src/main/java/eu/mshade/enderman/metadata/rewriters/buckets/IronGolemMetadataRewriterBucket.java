package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsPlayerCreatedMetadataRewriter;

public class IronGolemMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public IronGolemMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_PLAYER_CREATED, new IsPlayerCreatedMetadataRewriter());
    }
}
