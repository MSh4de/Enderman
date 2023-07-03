package eu.mshade.enderman.metadata.itemstack

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapper
import eu.mshade.enderframe.item.metadata.MapDecorationsItemStackMetadata
import eu.mshade.enderframe.map.MapCursorKey
import eu.mshade.enderframe.map.MapDecoration
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.mwork.binarytag.BinaryTagType
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag
import eu.mshade.mwork.binarytag.entity.ListBinaryTag

class MapDecorationsItemStackMetadataWrapper(private val cursorKeyWrapper: Wrapper<MapCursorKey, Int>?): ItemStackMetadataWrapper {

    override fun write(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadatas = itemStack.metadatas
        val mapDecorationsItemStackMetadata =
            metadatas.getMetadataKeyValue(ItemStackMetadataKey.MAP_DECORATIONS) as? MapDecorationsItemStackMetadata
                ?: return

        val decorationsListTag = ListBinaryTag(BinaryTagType.COMPOUND)

        for (mapDecoration in mapDecorationsItemStackMetadata.metadataValue) {
            val binaryTag = CompoundBinaryTag()
            val cursorType = cursorKeyWrapper?.map(mapDecoration.type)?: continue

            binaryTag.putString("id", mapDecoration.id)
            binaryTag.putByte("type", cursorType.toByte())
            binaryTag.putDouble("x", mapDecoration.x)
            binaryTag.putDouble("z", mapDecoration.z)
            binaryTag.putDouble("rot", mapDecoration.rot)

            decorationsListTag.add(binaryTag)
        }

        if (decorationsListTag.isEmpty()) return

        compoundBinaryTag.putBinaryTag("Decorations", decorationsListTag)

    }

    override fun read(compoundBinaryTag: CompoundBinaryTag, itemStack: ItemStack) {
        val metadatas = itemStack.metadatas
        val decorationsListTag = compoundBinaryTag.getBinaryTag("Decorations") as? ListBinaryTag ?: return

        val decorations = mutableListOf<MapDecoration>()

        for (entry in decorationsListTag.value) {
            if (entry !is CompoundBinaryTag) continue

            val id = entry.getString("id")?: continue
            val type = cursorKeyWrapper?.reverseMap(entry.getByte("type").toInt())?: continue
            val x = entry.getDouble("x")
            val z = entry.getDouble("z")
            val rot = entry.getDouble("rot")

            decorations.add(MapDecoration(id, type, x, z, rot))
        }

        metadatas.setMetadataKeyValue(MapDecorationsItemStackMetadata(decorations))

    }

}