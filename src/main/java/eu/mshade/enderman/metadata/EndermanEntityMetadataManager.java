package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.*;
import eu.mshade.enderman.metadata.entity.ArmorStandMetadataBucket;
import eu.mshade.enderman.metadata.entity.PlayerMetadataBucket;

public class EndermanEntityMetadataManager extends EntityMetadataManager {

    public EndermanEntityMetadataManager() {
        this.registerMetadataIndex(0, MetadataType.BYTE, (byteMessage, metadata) -> byteMessage.writeByte((byte) metadata.get()));
        this.registerMetadataIndex(1, MetadataType.SHORT, (byteMessage, metadata) -> byteMessage.writeShort((short) metadata.get()));
        this.registerMetadataIndex(2, MetadataType.INTEGER, (byteMessage, metadata) -> byteMessage.writeInt((int) metadata.get()));
        this.registerMetadataIndex(3, MetadataType.FLOAT, (byteMessage, metadata) -> byteMessage.writeFloat((float) metadata.get()));
        this.registerMetadataIndex(4, MetadataType.STRING, (byteMessage, metadata) -> byteMessage.writeString((CharSequence) metadata.get()));
        //this.registerMetadataIndex(5, MetadataType.SLOT, (byteMessage, metadata) -> byteMessage.writeByte((int) metadata.get()));
        this.registerMetadataIndex(6, MetadataType.BLOCK_POSITION, new BlockPositionMetadataTypeBuffer());
        this.registerMetadataIndex(7, MetadataType.ROTATION, new RotationMetadataTypeBuffer());

        this.registerEntityMetadataBucket(EntityType.PLAYER, new PlayerMetadataBucket());
        this.registerEntityMetadataBucket(EntityType.ARMOR_STAND, new ArmorStandMetadataBucket());

        /*
        registerEntityBuffer(EntityType.PLAYER, new PlayerMetadataBuffer());
        registerEntityBuffer(EntityType.ZOMBIE, new ZombieMetadataBuffer());
        registerEntityBuffer(EntityType.END_CRYSTAL, new EnderCrystalMetadataBuffer());
        registerEntityBuffer(EntityType.BOAT, new BoatMetadataBuffer());
        registerEntityBuffer(EntityType.SPIDER, new SpiderMetadataBuffer());

         */


        /*
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

         */
    }
}
