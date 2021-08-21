package eu.mshade.enderman.metadata.rewriters.buckets;

import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderman.metadata.rewriters.*;

public class ArmorStandMetadataRewriterBucket extends LivingEntityMetadataRewriterBucket {

    public ArmorStandMetadataRewriterBucket() {
        registerMetadataRewriter(MetadataMeaning.ARMOR_STAND_PROPERTIES, new ArmorStandPropertiesMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.HEAD_ROTATION, new HeadRotationMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.BODY_ROTATION, new BodyRotationMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.LEFT_ARM_ROTATION, new LeftArmRotationMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.RIGHT_ARM_ROTATION, new RightArmRotationMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.LEFT_LEG_ROTATION, new LeftLegRotationMetadataRewriter());
        registerMetadataRewriter(MetadataMeaning.RIGHT_LEG_ROTATION, new RightLegRotationMetadataRewriter());
    }
}
