package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.world.block.*
import eu.mshade.enderframe.wrapper.MaterialKeyWrapper
import eu.mshade.enderframe.wrapper.MaterialWrapperContext

class StairsBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val blockFace = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE)?.metadataValue
            ?: BlockFace.NONE) as BlockFace
        val halfBlock = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.HALF)?.metadataValue
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


        val wrap = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())

        return MaterialKey.from(wrap!!.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block {
        val blockFaceId = material.metadata and 0x3
        val halfBlockId = material.metadata and 0x4

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


        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id))

        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(halfBlock))

        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        val name = material.namespacedKey.key
        return name.endsWith("_stairs")
    }

}

class LogBlockTransFormer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val logAxis = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.AXIS)?.metadataValue
            ?: BlockAxis.NONE) as BlockAxis


        var value = 0
        if (logAxis == BlockAxis.X) {
            value = 4
        } else if (logAxis == BlockAxis.Z) {
            value = 8
        } else if (logAxis == BlockAxis.NONE) {
            value = 12
        }

        val wrap = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())
        return MaterialKey.from(wrap!!.id, wrap.metadata + value)

    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val metadata = material.metadata
        val metadataWithOutAxis = metadata and 0x3
        val wrap =
            materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id, metadataWithOutAxis))
        val block = wrap?.toBlock() ?: return null
        val metadataKeyValueBucket = block.getMetadatas()

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

    override fun canTransform(material: MaterialKey): Boolean {
        val name = material.namespacedKey.key
        return name.endsWith("_wood")
    }

}

class ButtonBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()

        val blockFace = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE).metadataValue
            ?: BlockFace.NONE) as BlockFace

        val powered = block.hasPower()

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


        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())

        return MaterialKey.from(materialKey!!.id, value)

    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block {
        val metadata = material.metadata
        val powered = metadata and 8 == 8

        val blockFace = when (metadata and 7) {
            1 -> BlockFace.EAST
            2 -> BlockFace.WEST
            3 -> BlockFace.SOUTH
            4 -> BlockFace.NORTH
            5 -> BlockFace.UP
            else -> BlockFace.NONE
        }

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PowerBlockMetadata(powered))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        val name = material.namespacedKey.key
        return name.endsWith("_button")
    }

}

class LeverBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val blockFace = block.getFace()
        val powered = block.hasPower()
        val axis = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.AXIS).metadataValue
            ?: BlockAxis.X) as BlockAxis

        var value = when (blockFace) {
            BlockFace.EAST -> 1
            BlockFace.WEST -> 2
            BlockFace.SOUTH -> 3
            BlockFace.NORTH -> 4
            BlockFace.UP -> {
                if (axis == BlockAxis.Z) 5
                else 6
            }

            BlockFace.DOWN -> {
                if (axis == BlockAxis.Z) 7
                else 0
            }

            else -> 0
        }

        if (powered) {
            value = value or 8
        }

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())

        return MaterialKey.from(materialKey!!.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block {
        val metadata = material.metadata
        val powered = metadata and 8 == 8

        val blockFace = when (metadata and 7) {
            0 -> BlockFace.DOWN
            1 -> BlockFace.EAST
            2 -> BlockFace.WEST
            3 -> BlockFace.SOUTH
            4 -> BlockFace.NORTH
            5 -> BlockFace.UP
            6 -> BlockFace.UP
            7 -> BlockFace.DOWN
            else -> BlockFace.NONE
        }

        val axis = if (metadata and 7 == 5 || metadata and 7 == 6) BlockAxis.Z else BlockAxis.X

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PowerBlockMetadata(powered))
        metadataKeyValueBucket.setMetadataKeyValue(AxisBlockMetadata(axis))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material == Material.LEVER
    }


}

class SlabBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val metadataKeyValueBucket = block.getMetadatas()
        val half = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.HALF).metadataValue
            ?: BlockHalf.BOTTOM) as BlockHalf

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null
        var value = materialKey.metadata

        if (half == BlockHalf.TOP) {
            value += 8
        }


        return MaterialKey.from(materialKey.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val metadata = material.metadata
        val half = if (metadata and 8 == 8) BlockHalf.TOP else BlockHalf.BOTTOM

        val wrap =
            materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id)) ?: return null
        val block = wrap.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(half))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material.namespacedKey.key.endsWith("_slab") && !material.namespacedKey.key.startsWith("double_")
    }

}

class DoubleSlabBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val metadataKeyValueBucket = block.getMetadatas()
        val seamless = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.SEAMLESS)?.metadataValue as? Boolean
            ?: false

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null
        var value = materialKey.metadata

        if (seamless) {
            value += 8
        }

        return MaterialKey.from(materialKey.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val metadata = material.metadata
        val seamless = metadata and 8 == 8

        val wrap =
            materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id)) ?: return null
        val block = wrap.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(SeamlessBlockMetadata(seamless))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material.namespacedKey.key.startsWith("double_") && material.namespacedKey.key.endsWith("_slab")
    }


}


class LeavesBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val decayable =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.DECAYABLE)?.metadataValue as? Boolean ?: false
        val checkDecay =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.CHECK_DECAY)?.metadataValue as? Boolean
                ?: false


        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())
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

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val metadata = material.metadata
        var decayable = false
        var checkDecay = false

        if (metadata < 3 || (metadata in 8..11)) decayable = true


        if (metadata in 7..15) checkDecay = true


        val wrap =
            materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id, metadata and 3))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(DecayableBlockMetadata(decayable))
        metadataKeyValueBucket.setMetadataKeyValue(CheckDecayBlockMetadata(checkDecay))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        val name = material.namespacedKey.key
        return name.endsWith("_leaves")
    }

}

class VineBlockTransformer : BlockTransformer() {

    val idFromBlockFace = mapOf(
        BlockFace.NORTH to 1,
        BlockFace.EAST to 2,
        BlockFace.SOUTH to 4,
        BlockFace.WEST to 8,
    )

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val multipleFace =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.MULTIPLE_FACE)?.metadataValue as? Set<BlockFace>
                ?: emptySet()

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())
        var data = 0
        multipleFace.forEach {
            data += idFromBlockFace[it] ?: 0
        }

        if (data == 0) data = 1

        return MaterialKey.from(materialKey!!.id, data)
    }


    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val metadata = material.metadata
        val multipleFace = mutableSetOf<BlockFace>()

        idFromBlockFace.forEach {
            if (metadata and it.value == it.value) {
                multipleFace.add(it.key)
            }
        }

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(material.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadatas()
        metadataKeyValueBucket.setMetadataKeyValue(MultipleFaceBlockMetadata(multipleFace))
        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material == Material.VINE
    }

}

class BedBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey {
        val metadataKeyValueBucket = block.getMetadatas()
        val part = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.HALF)?.metadataValue as? BlockHalf
            ?: BlockHalf.BOTTOM

        val face = block.getFace()

        //TODO: check if it is occupied

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial())
        var value = materialKey!!.metadata

        value = when (face) {
            BlockFace.NORTH -> 2
            BlockFace.SOUTH -> 0
            BlockFace.WEST -> 1
            BlockFace.EAST -> 3
            else -> 0
        }

        if (part == BlockHalf.TOP) {
            value = value or 8
        }

        return MaterialKey.from(materialKey.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val id = material.id
        val metadata = material.metadata
        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(id))
        val block = wrap?.toBlock() ?: return null
        val metadataKeyValueBucket = block.getMetadatas()

        val face = when (metadata and 3) {
            0 -> BlockFace.SOUTH
            1 -> BlockFace.WEST
            2 -> BlockFace.NORTH
            3 -> BlockFace.EAST
            else -> BlockFace.NORTH
        }

        val part = if (metadata and 8 == 8) BlockHalf.TOP else BlockHalf.BOTTOM

        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(face))
        metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(part))

        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material.namespacedKey.key.endsWith("_bed")
    }
}


class ChestTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val metadataKeyValueBucket = block.getMetadatas()
        val facing = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE)?.metadataValue as? BlockFace
            ?: BlockFace.NORTH

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null

        val value = when (facing) {
            BlockFace.NORTH -> 2
            BlockFace.SOUTH -> 3
            BlockFace.WEST -> 4
            BlockFace.EAST -> 5
            else -> 0
        }

        return MaterialKey.from(materialKey.id, value)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val id = material.id
        val metadata = material.metadata

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(id))

        val block = wrap?.toBlock() ?: return null

        val metadataKeyValueBucket = block.getMetadatas()

        val facing = when (metadata and 7) {
            2 -> BlockFace.NORTH
            3 -> BlockFace.SOUTH
            4 -> BlockFace.WEST
            5 -> BlockFace.EAST
            else -> BlockFace.NORTH
        }

        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(facing))

        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material.namespacedKey.key.endsWith("_chest") || material.namespacedKey.key.startsWith("chest")
    }
}

class RedstoneLampTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val powered = block.hasPower()

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null
        var id = materialKey.id
        if (powered) {
            id += 1
        }

        return MaterialKey.from(id)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val id = material.id

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(id))

        val block = wrap?.toBlock() ?: return null

        val metadataKeyValueBucket = block.getMetadatas()

        val lit = id % 2 == 1

        metadataKeyValueBucket.setMetadataKeyValue(PowerBlockMetadata(lit))

        return block

    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material == Material.REDSTONE_LAMP
    }
}

class RedstoneWireBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val power = block.getPower()

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null
        return MaterialKey.from(materialKey.id, power)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val id = material.id

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(id))

        val block = wrap?.toBlock() ?: return null

        val metadataKeyValueBucket = block.getMetadatas()

        val power = material.metadata

        metadataKeyValueBucket.setMetadataKeyValue(PowerBlockMetadata(power))

        return block

    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material == Material.REDSTONE_WIRE
    }
}

class RepeatersBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: MaterialKeyWrapper): MaterialKey? {
        val metadataKeyValueBucket = block.getMetadatas()

        val delay = (metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.DELAY)?.metadataValue as? Int
            ?: 0) and 3
        val blockFace = metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.FACE)?.metadataValue as? BlockFace
            ?: BlockFace.NORTH

        val powered = block.hasPower()

        val materialKey = materialWrapper.map(MaterialWrapperContext.BLOCK, block.getMaterial()) ?: return null
        var metadata = 0
        var id = materialKey.id

        if (powered) {
            id += 1
        }

        metadata = when (blockFace) {
            BlockFace.SOUTH -> 0
            BlockFace.WEST -> 1
            BlockFace.NORTH -> 2
            BlockFace.EAST -> 3
            else -> 0
        }

        metadata = metadata or (delay shl 2)

        return MaterialKey.from(id, metadata)
    }

    override fun transform(material: MaterialKey, materialWrapper: MaterialKeyWrapper): Block? {
        val id = material.id

        val wrap = materialWrapper.reverseMap(MaterialWrapperContext.BLOCK, MaterialKey.from(id))

        val block = wrap?.toBlock() ?: return null

        val metadataKeyValueBucket = block.getMetadatas()

        val metadata = material.metadata

        val delay = metadata shr 2
        val blockFace = when (metadata and 3) {
            0 -> BlockFace.SOUTH
            1 -> BlockFace.WEST
            2 -> BlockFace.NORTH
            3 -> BlockFace.EAST
            else -> BlockFace.NORTH
        }

        val powered = id % 2 == 1

        metadataKeyValueBucket.setMetadataKeyValue(DelayBlockMetadata(delay))
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PowerBlockMetadata(powered))

        return block
    }

    override fun canTransform(material: MaterialKey): Boolean {
        return material == Material.REPEATER
    }
}