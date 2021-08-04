package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.IsChildMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.IsConvertingMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.IsVillagerMetadataRewriter;

public class ZombieMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public ZombieMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CHILD, new IsChildMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_VILLAGER, new IsVillagerMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_CONVERTING, new IsConvertingMetadataRewriter());
    }
}
