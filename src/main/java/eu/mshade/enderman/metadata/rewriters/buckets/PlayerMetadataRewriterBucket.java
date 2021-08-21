package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.AbsorptionHeartsMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.ScoreMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.SkinPartsMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.UnusedMetadataRewriter;

public class PlayerMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public PlayerMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.SKIN_PART, new SkinPartsMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.UNUSED, new UnusedMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.ABSORPTION_HEARTS, new AbsorptionHeartsMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.SCORE, new ScoreMetadataRewriter());
    }
}
