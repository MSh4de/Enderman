package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.AbsorptionHeartsMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.ScoreMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.SkinPartsMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.UnusedMetadataRewriter;

public class PlayerMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public PlayerMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.SKIN_PART, new SkinPartsMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.UNUSED, new UnusedMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.ABSORPTION_HEARTS, new AbsorptionHeartsMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.SCORE, new ScoreMetadataRewriter());
    }
}
