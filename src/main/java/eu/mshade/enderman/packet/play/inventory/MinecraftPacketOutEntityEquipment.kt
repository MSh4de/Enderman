package eu.mshade.enderman.packet.play.inventory

import eu.mshade.enderframe.entity.Entity
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketOut

class MinecraftPacketOutEntityEquipment(private val entity: Entity, private val equipmentSlot: Int, private val itemStack: ItemStack?): MinecraftPacketOut {

    override fun serialize(minecraftByteBuf: MinecraftByteBuf) {
        minecraftByteBuf.writeVarInt(entity.getEntityId())
        minecraftByteBuf.writeShort(equipmentSlot)
        minecraftByteBuf.writeItemStack(itemStack)
    }

}