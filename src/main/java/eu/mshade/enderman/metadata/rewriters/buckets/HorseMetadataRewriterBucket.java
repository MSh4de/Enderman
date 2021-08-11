package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.*;
import eu.mshade.enderman.metadata.v2.rewriters.*;

public class HorseMetadataRewriterBucket extends AgeableMetadataRewriterBucket {

    public HorseMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.HORSE_PROPERTIES, new HorsePropertiesMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HORSE_TYPE, new HorseTypeMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HORSE_COLOR, new HorseColorMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HORSE_STYLE, new HorseStyleMetadataWriter());
        registerMetadataRewriter(MetadataMeaning.OWNER, new OwnerMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HORSE_ARMOR, new HorseArmorMetadataRewriter());
    }
}
