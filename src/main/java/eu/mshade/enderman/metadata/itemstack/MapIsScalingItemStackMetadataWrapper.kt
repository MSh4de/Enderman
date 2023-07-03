package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.MapIsScalingItemStackMetadata
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag

class MapIsScalingItemStackMetadataWrapper : ItemStackMetadataWrapper {

    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadatas = itemStack.metadatas
        val mapIsScalingItemStackMetadata =
            metadatas.getMetadataKeyValue(ItemStackMetadataKey.MAP_IS_SCALING) as? MapIsScalingItemStackMetadata
                ?: return

        compoundBinaryTag.putByte("map_is_scaling", mapIsScalingItemStackMetadata.metadataValue)
    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val mapIsScalingItemStackMetadata = MapIsScalingItemStackMetadata(compoundBinaryTag.getByte("map_is_scaling") == 1.toByte())
        itemStack.metadatas.setMetadataKeyValue(mapIsScalingItemStackMetadata)
    }

}
