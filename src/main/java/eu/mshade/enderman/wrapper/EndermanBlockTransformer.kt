package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.item.MaterialCategory
import eu.mshade.enderframe.item.MaterialCategoryKey
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.world.block.*
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.enderman.EndermanMaterial

class StairsBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val blockFace = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE).metadataValue
            ?: BlockFace.NONE) as BlockFace
        val halfBlock = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.HALF).metadataValue
            ?: BlockHalf.BOTTOM) as BlockHalf

        var blockFaceId = 0
        if (blockFace == BlockFace.NORTH) {
            blockFaceId = 3
        } else if (blockFace == BlockFace.SOUTH) {
            blockFaceId = 2
        } else if (blockFace == BlockFace.WEST) {
            blockFaceId = 1
        } else if (blockFace == BlockFace.EAST) {
            blockFaceId = 0
        }

        var value = blockFaceId;
        if (halfBlock == BlockHalf.TOP) {
            value = value or 0x4
        }


        val wrap = materialWrapper.map(block.getMaterialKey())

        return MaterialKey.from(wrap!!.id, value)
    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block {
        val blockFaceId = materialKey.metadata and 0x3
        val halfBlockId = materialKey.metadata and 0x4

        var blockFace = BlockFace.NONE
        if (blockFaceId == 3) {
            blockFace = BlockFace.NORTH
        } else if (blockFaceId == 2) {
            blockFace = BlockFace.SOUTH
        } else if (blockFaceId == 1) {
            blockFace = BlockFace.WEST
        } else if (blockFaceId == 0) {
            blockFace = BlockFace.EAST
        }

        var halfBlock = BlockHalf.BOTTOM
        if (halfBlockId == 0x4) {
            halfBlock = BlockHalf.TOP
        }


        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id))

        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(halfBlock))

        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.STAIRS
    }


}

class LogBlockTransFormer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val logAxis = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.AXIS).metadataValue
            ?: BlockAxis.NONE) as BlockAxis


        var value = 0
        if (logAxis == BlockAxis.X) {
            value = 4
        } else if (logAxis == BlockAxis.Z) {
            value = 8
        } else if (logAxis == BlockAxis.NONE) {
            value = 12
        }

        val wrap = materialWrapper.map(block.getMaterialKey())
        return MaterialKey.from(wrap!!.id, wrap.metadata + value)

    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val metadata = materialKey.metadata
        val metadataWithOutAxis = metadata and 0x3
        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id, metadataWithOutAxis))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()

        var logAxis = BlockAxis.NONE

        if (metadata < 4) {
            logAxis = BlockAxis.Y
        } else if (metadata < 8) {
            logAxis = BlockAxis.X
        } else if (metadata < 12) {
            logAxis = BlockAxis.Z
        }

        metadataKeyValueBucket.setMetadataKeyValue(AxisBlockMetadata(logAxis))
        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.LOG
    }

}

class ButtonBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()

        val blockFace = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE).metadataValue
            ?: BlockFace.NONE) as BlockFace

        val powered =
            metadataKeyValueBucket.getMetadataKeyValue(
                BlockMetadataType.POWERED
            )?.metadataValue as? Boolean ?: false

        var value = when (blockFace) {
            BlockFace.EAST -> 1
            BlockFace.WEST -> 2
            BlockFace.SOUTH -> 3
            BlockFace.NORTH -> 4
            BlockFace.UP -> 5
            else -> 0
        }

        if (powered) {
            value += 8
        }


        val materialKey = materialWrapper.map(block.getMaterialKey())

        return MaterialKey.from(materialKey!!.id, value)

    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block {
        val metadata = materialKey.metadata
        val powered = metadata and 8 == 8

        val blockFace = when (metadata and 7) {
            1 -> BlockFace.EAST
            2 -> BlockFace.WEST
            3 -> BlockFace.SOUTH
            4 -> BlockFace.NORTH
            5 -> BlockFace.UP
            else -> BlockFace.NONE
        }

        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PoweredBlockMetadata(powered))
        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.BUTTON
    }

}

class LeverBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val blockFace = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE).metadataValue
            ?: BlockFace.NONE) as BlockFace
        val powered =
            metadataKeyValueBucket.getMetadataKeyValue(
                BlockMetadataType.POWERED
            )?.metadataValue as? Boolean ?: false
        val axis = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.AXIS).metadataValue
            ?: BlockAxis.X) as BlockAxis

        var value = when (blockFace) {
            BlockFace.EAST -> 1
            BlockFace.WEST -> 2
            BlockFace.SOUTH -> 3
            BlockFace.NORTH -> 4
            BlockFace.UP -> {
                if (axis == BlockAxis.X) 5
                else 6
            }

            BlockFace.DOWN -> {
                if (axis == BlockAxis.X) 7
                else 0
            }

            else -> 0
        }

        if (powered) {
            value = value or 8
        }

        val materialKey = materialWrapper.map(block.getMaterialKey())

        return MaterialKey.from(materialKey!!.id, value)
    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block {
        val metadata = materialKey.metadata
        val powered = metadata and 8 == 8

        val blockFace = when (metadata and 7) {
            1 -> BlockFace.EAST
            2 -> BlockFace.WEST
            3 -> BlockFace.SOUTH
            4 -> BlockFace.NORTH
            5 -> BlockFace.UP
            6 -> BlockFace.UP
            7 -> BlockFace.DOWN
            else -> BlockFace.NONE
        }

        val axis = when (metadata and 7) {
            5 -> BlockAxis.Z
            6 -> BlockAxis.X
            else -> BlockAxis.X
        }

        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PoweredBlockMetadata(powered))
        metadataKeyValueBucket.setMetadataKeyValue(AxisBlockMetadata(axis))
        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.LEVER
    }


}

class SlabBlockTransformer : BlockTransformer() {

    val slabToDoubleSlab = mapOf(
        Material.STONE_SLAB to EndermanMaterial.DOUBLE_STONE_SLAB,
        Material.SANDSTONE_SLAB to EndermanMaterial.DOUBLE_SANDSTONE_SLAB,
        Material.COBBLESTONE_SLAB to EndermanMaterial.DOUBLE_COBBLESTONE_SLAB,
        Material.BRICK_SLAB to EndermanMaterial.DOUBLE_BRICK_SLAB,
        Material.STONE_BRICK_SLAB to EndermanMaterial.DOUBLE_STONE_BRICK_SLAB,
        Material.NETHER_BRICK_SLAB to EndermanMaterial.DOUBLE_NETHER_BRICK_SLAB,
        Material.QUARTZ_SLAB to EndermanMaterial.DOUBLE_QUARTZ_SLAB,
        Material.RED_SANDSTONE_SLAB to EndermanMaterial.DOUBLE_RED_SANDSTONE_SLAB,
        Material.PURPUR_SLAB to EndermanMaterial.DOUBLE_PURPUR_SLAB,


        Material.OAK_SLAB to EndermanMaterial.DOUBLE_OAK_SLAB,
        Material.SPRUCE_SLAB to EndermanMaterial.DOUBLE_SPRUCE_SLAB,
        Material.BIRCH_SLAB to EndermanMaterial.DOUBLE_BIRCH_SLAB,
        Material.JUNGLE_SLAB to EndermanMaterial.DOUBLE_JUNGLE_SLAB,
        Material.ACACIA_SLAB to EndermanMaterial.DOUBLE_ACACIA_SLAB,
        Material.DARK_OAK_SLAB to EndermanMaterial.DOUBLE_DARK_OAK_SLAB,

        )

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val slabType = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.SLAB_TYPE)?.metadataValue as? SlabType ?: SlabType.BOTTOM

        val materialKey: MaterialKey
        var value = 0

        when (slabType) {
            SlabType.DOUBLE -> {
                materialKey = slabToDoubleSlab[block.getMaterialKey()]!!
                value = materialKey.metadata

                val seamless = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.SEAMLESS)?.metadataValue as? Boolean ?: false
                if (seamless) {
                    value = value or 8
                }
            }
            SlabType.TOP, SlabType.BOTTOM -> {
                materialKey = materialWrapper.map(block.getMaterialKey())!!
                value = materialKey.metadata

                if (slabType == SlabType.TOP) {
                    value = value or 8
                }
            }
        }

        return MaterialKey.from(materialKey.id, value)
    }


    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val id = materialKey.id
        val metadata = materialKey.metadata
        val wrap = materialWrapper.reverseMap(MaterialKey.from(id, metadata and 7))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()

        //check if it is a double slab
        if (setOf(43, 125, 181, 204).contains(id)) {
            val seamless = metadata and 8 == 8
            metadataKeyValueBucket.setMetadataKeyValue(SeamlessBlockMetadata(seamless))
        } else {
            val halfBlock = if (metadata and 8 == 8) BlockHalf.TOP else BlockHalf.BOTTOM
            metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(halfBlock))
        }

        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.SLAB
    }

}

class LeavesBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val decayable =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.DECAYABLE)?.metadataValue as? Boolean ?: false
        val checkDecay =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.CHECK_DECAY)?.metadataValue as? Boolean
                ?: false


        val materialKey = materialWrapper.map(block.getMaterialKey())
        var value = materialKey!!.metadata

        if (!decayable && !checkDecay) {
            value = value or 4
        } else if (decayable && checkDecay) {
            value = value or 8
        } else if (decayable && !checkDecay) {
            value = value or 12
        }

        return MaterialKey.from(materialKey.id, value)

    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val metadata = materialKey.metadata
        var decayable = false
        var checkDecay = false

        if (metadata < 3 || (metadata in 8..11)) decayable = true


        if (metadata in 7..15) checkDecay = true


        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id, metadata and 3))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(DecayableBlockMetadata(decayable))
        metadataKeyValueBucket.setMetadataKeyValue(CheckDecayBlockMetadata(checkDecay))
        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.LEAVES
    }

}

class VineBlockTransformer : BlockTransformer() {

    val idFromBlockFace = mapOf(
        BlockFace.NORTH to 1,
        BlockFace.EAST to 2,
        BlockFace.SOUTH to 4,
        BlockFace.WEST to 8,
    )

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val multipleFace =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.MULTIPLE_FACE)?.metadataValue as? Set<BlockFace>
                ?: emptySet()

        val materialKey = materialWrapper.map(block.getMaterialKey())
        var data = 0
        multipleFace.forEach {
            data += idFromBlockFace[it] ?: 0
        }

        if (data == 0) data = 1

        return MaterialKey.from(materialKey!!.id, data)
    }


    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val metadata = materialKey.metadata
        val multipleFace = mutableSetOf<BlockFace>()

        idFromBlockFace.forEach {
            if (metadata and it.value == it.value) {
                multipleFace.add(it.key)
            }
        }

        val wrap = materialWrapper.reverseMap(MaterialKey.from(materialKey.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(MultipleFaceBlockMetadata(multipleFace))
        return block
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.VINE
    }

}


class CommonBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        return materialWrapper.map(block.getMaterialKey())!!
    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val reverse = materialWrapper.reverseMap(materialKey) ?: return null
        return Block(reverse)
    }

    override fun getTag(): MaterialCategoryKey {
        return MaterialCategory.DEFAULT
    }

}