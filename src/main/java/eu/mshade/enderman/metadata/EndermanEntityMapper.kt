package eu.mshade.enderman.metadata

import eu.mshade.enderframe.entity.EntityCategory
import eu.mshade.enderframe.entity.EntityMapper
import eu.mshade.enderframe.entity.EntityType

class EndermanEntityMapper: EntityMapper() {

    init {
        //Map all entities to their category
        register(EntityType.CREEPER, EntityCategory.ENTITY)
        register(EntityType.SKELETON, EntityCategory.ENTITY)
        register(EntityType.SPIDER, EntityCategory.ENTITY)
        register(EntityType.GIANT, EntityCategory.ENTITY)
        register(EntityType.ZOMBIE, EntityCategory.ENTITY)
        register(EntityType.SLIME, EntityCategory.ENTITY)
        register(EntityType.GHAST, EntityCategory.ENTITY)
        register(EntityType.ZOMBIFIED_PIGLIN, EntityCategory.ENTITY)
        register(EntityType.ENDERMAN, EntityCategory.ENTITY)
        register(EntityType.CAVE_SPIDER, EntityCategory.ENTITY)
        register(EntityType.SILVERFISH, EntityCategory.ENTITY)
        register(EntityType.BLAZE, EntityCategory.ENTITY)
        register(EntityType.MAGMA_CUBE, EntityCategory.ENTITY)
        register(EntityType.ENDER_DRAGON, EntityCategory.ENTITY)
        register(EntityType.WITHER, EntityCategory.ENTITY)
        register(EntityType.BAT, EntityCategory.ENTITY)
        register(EntityType.WITCH, EntityCategory.ENTITY)
        register(EntityType.ENDERMITE, EntityCategory.ENTITY)
        register(EntityType.GUARDIAN, EntityCategory.ENTITY)
        register(EntityType.PIG, EntityCategory.ENTITY)
        register(EntityType.SHEEP, EntityCategory.ENTITY)
        register(EntityType.COW, EntityCategory.ENTITY)
        register(EntityType.CHICKEN, EntityCategory.ENTITY)
        register(EntityType.SQUID, EntityCategory.ENTITY)
        register(EntityType.WOLF, EntityCategory.ENTITY)
        register(EntityType.MUSHROOM_COW, EntityCategory.ENTITY)
        register(EntityType.SNOWMAN, EntityCategory.ENTITY)
        register(EntityType.OCELOT, EntityCategory.ENTITY)
        register(EntityType.IRON_GOLEM, EntityCategory.ENTITY)
        register(EntityType.HORSE, EntityCategory.ENTITY)
        register(EntityType.RABBIT, EntityCategory.ENTITY)
        register(EntityType.VILLAGER, EntityCategory.ENTITY)

        register(EntityType.BOAT, EntityCategory.OBJECT)
        register(EntityType.MINECART, EntityCategory.OBJECT)
        register(EntityType.PRIMED_TNT, EntityCategory.OBJECT)
        register(EntityType.ENDER_CRYSTAL, EntityCategory.OBJECT)
        register(EntityType.ARROW, EntityCategory.OBJECT)
        register(EntityType.SNOWBALL, EntityCategory.OBJECT)
        register(EntityType.EGG, EntityCategory.OBJECT)
        register(EntityType.SMALL_FIREBALL, EntityCategory.OBJECT)
        register(EntityType.FIREBALL, EntityCategory.OBJECT)
        register(EntityType.ENDER_PEARL, EntityCategory.OBJECT)
        register(EntityType.WITHER_SKULL, EntityCategory.OBJECT)
        register(EntityType.FALLING_BLOCK, EntityCategory.OBJECT)
        register(EntityType.ITEM_FRAME, EntityCategory.OBJECT)
        register(EntityType.ENDER_SIGNAL, EntityCategory.OBJECT) //A voir si c'est endersignal ou ender eye
        register(EntityType.SPLASH_POTION, EntityCategory.OBJECT)
        register(EntityType.ENDER_EGG, EntityCategory.OBJECT)
        register(EntityType.THROWN_EXP_BOTTLE, EntityCategory.OBJECT)
        register(EntityType.FIREWORK, EntityCategory.OBJECT)
        register(EntityType.LEASH_HITCH, EntityCategory.OBJECT)
        register(EntityType.ARMOR_STAND, EntityCategory.OBJECT)
        register(EntityType.FISHING_HOOK, EntityCategory.OBJECT)
    }

}