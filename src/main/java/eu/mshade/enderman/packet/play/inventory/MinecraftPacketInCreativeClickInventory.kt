package eu.mshade.enderman.packet.play.inventory

import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.protocol.MinecraftByteBuf
import eu.mshade.enderframe.protocol.MinecraftPacketIn
import eu.mshade.enderframe.protocol.MinecraftSession

class MinecraftPacketInCreativeClickInventory : MinecraftPacketIn {

    private lateinit var minecraftSession: MinecraftSession
    var slot: Int = 0
    lateinit var itemStack: ItemStack

    override fun deserialize(minecraftSession: MinecraftSession, minecraftByteBuf: MinecraftByteBuf) {
        this.minecraftSession = minecraftSession
        slot = minecraftByteBuf.readShort().toInt()
        itemStack = minecraftByteBuf.readItemStack()
    }

    override fun getMinecraftSession(): MinecraftSession {
        return minecraftSession
    }

    override fun toString(): String {
        return "MinecraftPacketInCreativeClickInventory(slot=$slot, itemStack=$itemStack)"
    }

}