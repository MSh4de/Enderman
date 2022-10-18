package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.world.block.*
import eu.mshade.enderframe.wrapper.Wrapper

class StairsBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val blockFace =
            metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.FACE, BlockFace::class.java)
                ?: BlockFace.NONE
        val halfBlock = metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.HALF, BlockHalf::class.java)
            ?: BlockHalf.BOTTOM


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


        val wrap = materialWrapper.wrap(block.getMaterialKey())

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


        val wrap = materialWrapper.reverse(MaterialKey.from(materialKey.id))

        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(HalfBlockMetadata(halfBlock))

        return block
    }


}

class LogBlockTransFormer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val logAxis = metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.AXIS, BlockAxis::class.java)
            ?: BlockAxis.NONE

        var value = 0
        if (logAxis == BlockAxis.X) {
            value = 4
        } else if (logAxis == BlockAxis.Z) {
            value = 8
        } else if (logAxis == BlockAxis.NONE) {
            value = 12
        }

        val wrap = materialWrapper.wrap(block.getMaterialKey())
        return MaterialKey.from(wrap!!.id, wrap.metadata + value)

    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val metadata = materialKey.metadata
        val metadataWithOutAxis = metadata or 3
        val wrap = materialWrapper.reverse(MaterialKey.from(materialKey.id, metadataWithOutAxis))
        val block = wrap?.toBlock()?: return null
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()

        var logAxis = BlockAxis.NONE
        if (metadata < 4) {
            logAxis = BlockAxis.Y;
        } else if (metadata < 8) {
            logAxis = BlockAxis.X;
        } else if (metadata < 12) {
            logAxis = BlockAxis.Z;
        }

        metadataKeyValueBucket.setMetadataKeyValue(AxisBlockMetadata(logAxis))
        return block
    }

}

class ButtonBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val blockFace = metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.FACE, BlockFace::class.java)
            ?: BlockFace.NONE

        val powered =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.POWERED, PoweredBlockMetadata::class.java)?.metadataValue ?: false

        var value = 0;
        if (blockFace == BlockFace.EAST) {
            value = 1
        } else if (blockFace == BlockFace.WEST) {
            value = 2
        } else if (blockFace == BlockFace.SOUTH) {
            value = 3
        } else if (blockFace == BlockFace.NORTH) {
            value = 4
        } else if (blockFace == BlockFace.UP) {
            value = 5
        }

        if (powered) {
            value += 8
        }


        val materialKey = materialWrapper.wrap(block.getMaterialKey())

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

        val wrap = materialWrapper.reverse(MaterialKey.from(materialKey.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PoweredBlockMetadata(powered))
        return block
    }

}

class LeverBlockTransformer: BlockTransformer(){

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        val blockFace = metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.FACE, BlockFace::class.java)
            ?: BlockFace.NONE
        val powered =
            metadataKeyValueBucket.getMetadataKeyValue(BlockMetadataType.POWERED, PoweredBlockMetadata::class.java)?.metadataValue ?: false
        val axis = metadataKeyValueBucket.getValueOfMetadataKeyValue(BlockMetadataType.AXIS, BlockAxis::class.java)
            ?: BlockAxis.X

        var value = 0;
        if (blockFace == BlockFace.EAST) {
            value = 1
        } else if (blockFace == BlockFace.WEST) {
            value = 2
        } else if (blockFace == BlockFace.SOUTH) {
            value = 3
        } else if (blockFace == BlockFace.NORTH) {
            value = 4
        }


        if (blockFace == BlockFace.UP) {
            if (axis == BlockAxis.Z) {
                value = 5
            } else {
                value = 6
            }
        } else if (blockFace == BlockFace.DOWN) {
            if (axis == BlockAxis.Z) {
                value = 7
            } else {
                value = 0
            }
        }


        if (powered) {
            value = value or 8
        }

        val materialKey = materialWrapper.wrap(block.getMaterialKey())

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

        val wrap = materialWrapper.reverse(MaterialKey.from(materialKey.id))
        val block = wrap!!.toBlock()
        val metadataKeyValueBucket = block.getMetadataKeyValueBucket()
        metadataKeyValueBucket.setMetadataKeyValue(FaceBlockMetadata(blockFace))
        metadataKeyValueBucket.setMetadataKeyValue(PoweredBlockMetadata(powered))
        metadataKeyValueBucket.setMetadataKeyValue(AxisBlockMetadata(axis))
        return block
    }


}


class CommonBlockTransformer : BlockTransformer() {

    override fun transform(block: Block, materialWrapper: Wrapper<MaterialKey, MaterialKey>): MaterialKey {
        return materialWrapper.wrap(block.getMaterialKey())!!

    }

    override fun transform(materialKey: MaterialKey, materialWrapper: Wrapper<MaterialKey, MaterialKey>): Block? {
        val reverse = materialWrapper.reverse(materialKey)?: return null
        return Block(reverse)
    }

}