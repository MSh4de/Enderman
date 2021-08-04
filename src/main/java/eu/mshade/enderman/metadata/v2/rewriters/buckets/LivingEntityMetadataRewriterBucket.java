package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.*;

public class LivingEntityMetadataRewriterBucket extends EntityMetadataRewriterBucket{

    public LivingEntityMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.HEALTH, new HealthMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.POTION_EFFECT_COLOR, new PotionEffectColorRewriter());
        registerMetadataRewriter(MetadataMeaning.IS_POTION_EFFECT_AMBIENT, new IsPotionEffectAmbientMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.NUMBER_OF_ARROWS_IN_ENTITY, new NumberOfArrowsInEntityMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.WHETHER_AI, new WhetherAIMetadataRewriter());
    }
}
