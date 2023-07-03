package eu.mshade.enderman.`object`

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.entity.EntityKey
import eu.mshade.enderframe.entity.EntityType
import eu.mshade.enderframe.entity.ItemFrame
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.world.block.BlockFace
import eu.mshade.enderframe.world.block.BlockMetadataType
import eu.mshade.enderframe.world.block.BlockTransformerController
import java.util.UUID

interface EndermanObjectTransformer {

    fun transform(entity: Entity): Int
}

class EndermanObjectTransformerRepository(blockTransformerController: BlockTransformerController) {

    private val transformerByEntityType = mutableMapOf<EntityKey, EndermanObjectTransformer>()

    init {
        register(EntityType.ITEM_FRAME, ItemFrameObjectTransformer())
        register(EntityType.FALLING_BLOCK, FallingBlockObjectTransformer(blockTransformerController))
        register(EntityType.FISHING_HOOK, FishingHookObjectTransformer())

        val projectileObjectTransformer = ProjectileObjectTransformer()

        register(EntityType.ARROW, projectileObjectTransformer)
        register(EntityType.SNOWBALL, projectileObjectTransformer)
        register(EntityType.EGG, projectileObjectTransformer)
        register(EntityType.SMALL_FIREBALL, projectileObjectTransformer)
        register(EntityType.FIREBALL, projectileObjectTransformer)
        register(EntityType.WITHER_SKULL, projectileObjectTransformer)
    }

    fun register(entityKey: EntityKey, endermanObjectTransformer: EndermanObjectTransformer) {
        transformerByEntityType[entityKey] = endermanObjectTransformer
    }

    fun transform(entity: Entity): Int {
        val endermanObjectTransformer = transformerByEntityType[entity.entityKey] ?: return 0
        return endermanObjectTransformer.transform(entity)
    }
}

class ItemFrameObjectTransformer : EndermanObjectTransformer {

    companion object {
        val idFromOrientation = mapOf(
                BlockFace.DOWN to 0,
                BlockFace.UP to 1,
                BlockFace.NORTH to 2,
                BlockFace.SOUTH to 3,
                BlockFace.WEST to 4,
                BlockFace.EAST to 5,
        )
    }

    override fun transform(entity: Entity): Int {
        entity as ItemFrame
        val blockFace = (entity.metadata.getMetadataKeyValue(BlockMetadataType.FACE).metadataValue ?: BlockFace.DOWN) as BlockFace
        return idFromOrientation[blockFace] ?: 0
    }
}

class FallingBlockObjectTransformer(private val blockTransformerController: BlockTransformerController) : EndermanObjectTransformer {

    override fun transform(entity: Entity): Int {
        val item = (entity.metadata.getMetadataKeyValue(EntityMetadataKey.ITEM).metadataValue?: 0) as ItemStack
        val material = blockTransformerController.transform(item.material.toBlock())
        material ?: return 0

        return material.id shl 4 or (material.metadata and 15)
    }
}

class FishingHookObjectTransformer : EndermanObjectTransformer {

    override fun transform(entity: Entity): Int {
        val ownerUuid = entity.metadata.getMetadataKeyValue(EntityMetadataKey.OWNER).metadataValue as UUID
        val owner = entity.getLocation().world.getEntityByUuid(ownerUuid)

        return owner?.getEntityId() ?: 0
    }
}

class ProjectileObjectTransformer : EndermanObjectTransformer {

    override fun transform(entity: Entity): Int {
        val ownerUuid = entity.metadata.getMetadataKeyValue(EntityMetadataKey.OWNER).metadataValue as? UUID?: return 0
        //Todo
        val owner = entity.getLocation().world.getEntityByUuid(ownerUuid)

        return owner?.getEntityId() ?: 0
    }
}