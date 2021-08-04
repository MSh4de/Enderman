package eu.mshade.enderman.metadata.v2.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.v2.rewriters.DamageTakenMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.ForwardDirectionMetadataRewriter;
import eu.mshade.enderman.metadata.v2.rewriters.TimeSinceHitMetadataRewriter;

public class BoatMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public BoatMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.TIME_SINCE_LAST_HIT, new TimeSinceHitMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.FORWARD_DIRECTION, new ForwardDirectionMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.DAMAGE_TAKEN, new DamageTakenMetadataRewriter());
    }
}
