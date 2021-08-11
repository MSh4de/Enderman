package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.*;
import eu.mshade.enderman.metadata.v2.rewriters.*;

public class MinecartMetadataRewriterBucket extends EntityMetadataRewriterBucket {

    public MinecartMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.SHAKING_POWER, new ShakingPowerMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.SHAKING_DIRECTION, new ShakingDirectionMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.SHAKING_MULTIPLIER, new DamageTakenMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.MINECART_PROPERTIES, new MinecartProperties());
        registerMetadataRewriter(MetadataMeaning.BLOCK_Y_POSITION, new BlockYPositionMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.SHOW_BLOCK, new ShowBlockMetadataRewriter());
    }
}
