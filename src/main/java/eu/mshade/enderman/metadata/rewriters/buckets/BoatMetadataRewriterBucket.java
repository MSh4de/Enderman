package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.DamageTakenMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.ForwardDirectionMetadataRewriter;
import eu.mshade.enderman.metadata.rewriters.TimeSinceHitMetadataRewriter;

public class BoatMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public BoatMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.TIME_SINCE_LAST_HIT, new TimeSinceHitMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.FORWARD_DIRECTION, new ForwardDirectionMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.DAMAGE_TAKEN, new DamageTakenMetadataRewriter());
    }
}
