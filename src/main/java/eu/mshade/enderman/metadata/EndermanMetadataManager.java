package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.MetadataTypeRepository;
import eu.mshade.enderframe.metadata.buffer.MetadataBuffer;
import eu.mshade.enderframe.metadata.buffer.type.ByteMetadataTypeBuffer;
import eu.mshade.enderframe.metadata.buffer.type.FloatMetadataTypeBuffer;
import eu.mshade.enderframe.metadata.buffer.type.IntegerMetadataTypeBuffer;
import eu.mshade.enderframe.metadata.buffer.type.ShortMetadataTypeBuffer;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.metadata.v2.MetadataRewriterBucket;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderman.metadata.entity.*;
import eu.mshade.enderman.metadata.v2.rewriters.buckets.*;

public class EndermanMetadataManager extends MetadataManager {

    public EndermanMetadataManager() {
        registerMetadataIndexes();
        registerEntitiesBuffer();
        registerMetadataTypesBuffer();
        registerMetadataRewritersBucket();
    }

    private void registerMetadataIndexes() {
        MetadataTypeRepository metadataTypeRepository = getMetadataTypeRepository();
        metadataTypeRepository.registerMetadataIndex(MetadataType.BYTE, 0);
        metadataTypeRepository.registerMetadataIndex(MetadataType.SHORT, 1);
        metadataTypeRepository.registerMetadataIndex(MetadataType.INTEGER, 2);
        metadataTypeRepository.registerMetadataIndex(MetadataType.FLOAT, 3);
        metadataTypeRepository.registerMetadataIndex(MetadataType.STRING, 4);
        metadataTypeRepository.registerMetadataIndex(MetadataType.SLOT, 5);
        metadataTypeRepository.registerMetadataIndex(MetadataType.BLOCK_POSITION, 6);
        metadataTypeRepository.registerMetadataIndex(MetadataType.ROTATION, 7);
    }

    private void registerEntitiesBuffer() {
        registerEntityBuffer(EntityType.PLAYER, new PlayerMetadataBuffer());
        registerEntityBuffer(EntityType.ZOMBIE, new ZombieMetadataBuffer());
        registerEntityBuffer(EntityType.END_CRYSTAL, new EnderCrystalMetadataBuffer());
        registerEntityBuffer(EntityType.BOAT, new BoatMetadataBuffer());
        registerEntityBuffer(EntityType.SPIDER, new SpiderMetadataBuffer());
    }

    private void registerMetadataTypesBuffer() {
        registerMetadataTypeBuffer(MetadataType.BYTE, new ByteMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.SHORT, new ShortMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.INTEGER, new IntegerMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.FLOAT, new FloatMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.STRING, new StringMetadataTypeBuffer());
        //registerMetadataTypeBuffer(MetadataType.SLOT, new ());
        registerMetadataTypeBuffer(MetadataType.BLOCK_POSITION, new BlockPositionMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.ROTATION, new RotationMetadataTypeBuffer());
    }

    private void registerMetadataRewritersBucket() {
        registerMetadataRewriterBucket(EntityType.ARMOR_STAND, new ArmorStandMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.ARROW, new ArrowMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.BAT, new BatMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.BLAZE, new BlazeMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.BOAT, new BoatMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.CREEPER, new CreeperMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.END_CRYSTAL, new EnderCrystalMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.ENDERMAN, new EndermanMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.FIREWORK_ROCKET, new FireworkMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.FURNACE_MINECART, new FurnaceMinecartMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.GHAST, new GhastMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.GUARDIAN, new GuardianMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.HORSE, new HorseMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.IRON_GOLEM, new IronGolemMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.ITEM_FRAME, new ItemFrameMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.ITEM, new ItemMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.MINECART, new MinecartMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.OCELOT, new OcelotMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.PIG, new PigMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.PLAYER, new PlayerMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.RABBIT, new RabbitMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.SHEEP, new SheepMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.SKELETON, new SkeletonMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.SLIME, new SlimeMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.SPIDER, new SpiderMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.VILLAGER, new VillagerMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.WITCH, new WitchMetadataRewriterBucket());
        //TODO RE-WORK THIS ! pas bon
        //duplication de code car c'est exactement la même chose pour les 3
        registerMetadataRewriterBucket(EntityType.WITHER, new WitherMetadataRewriterBucket(0));
        registerMetadataRewriterBucket(EntityType.WOLF, new WolfMetadataRewriterBucket());
        registerMetadataRewriterBucket(EntityType.ZOMBIE, new ZombieMetadataRewriterBucket());
    }


    @Override
    public void write(ByteMessage byteMessage, Entity entity, MetadataMeaning metadataMeaning) {
        MetadataBuffer entityBuffer = this.getEntityBuffer(entity.getType());
        MetadataRewriterBucket bucket = getMetadataRewriterBucket(entity.getType());
        MetadataRewriter rewriter = bucket.getMetadataRewriter(metadataMeaning);
        MetadataType metadataType = rewriter.getMetadataType();

        int i = (getMetadataTypeRepository().getMetadataIndex(metadataType)) << 5 | entityBuffer.getMetadataRepository().getMetadataIndex(metadataMeaning);
        byteMessage.writeByte(i);
        rewriter.write(this, byteMessage, entity);
    }
}
