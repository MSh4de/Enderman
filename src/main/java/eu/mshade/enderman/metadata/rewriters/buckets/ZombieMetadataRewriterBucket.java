package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.IsChildMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsConvertingMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.IsVillagerMetadataRewriter;

public class ZombieMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public ZombieMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.IS_CHILD, new IsChildMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_VILLAGER, new IsVillagerMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_CONVERTING, new IsConvertingMetadataRewriter());
    }
}
