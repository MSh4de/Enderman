package eu.mshade.enderman

import eu.mshade.enderframe.PlayerInfoBuilder
import eu.mshade.enderframe.attribute.AttributeKey
import eu.mshade.enderframe.effect.Effect
import eu.mshade.enderframe.effect.EffectKey
import eu.mshade.enderframe.entity.*
import eu.mshade.enderframe.inventory.*
import eu.mshade.enderframe.item.ItemStack
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.metadata.MetadataKey
import eu.mshade.enderframe.mojang.chat.TextComponent
import eu.mshade.enderframe.mojang.chat.TextPosition
import eu.mshade.enderframe.particle.Particle
import eu.mshade.enderframe.particle.ParticleKey
import eu.mshade.enderframe.protocol.MinecraftProtocolPipeline
import eu.mshade.enderframe.protocol.MinecraftProtocolStatus
import eu.mshade.enderframe.protocol.MinecraftSession
import eu.mshade.enderframe.protocol.packet.*
import eu.mshade.enderframe.scoreboard.Scoreboard
import eu.mshade.enderframe.scoreboard.ScoreboardLine
import eu.mshade.enderframe.scoreboard.ScoreboardAction
import eu.mshade.enderframe.scoreboard.ScoreboardLineAction
import eu.mshade.enderframe.scoreboard.team.Team
import eu.mshade.enderframe.sound.SoundEffect
import eu.mshade.enderframe.title.Title
import eu.mshade.enderframe.title.TitleAction
import eu.mshade.enderframe.world.*
import eu.mshade.enderframe.world.Vector
import eu.mshade.enderframe.world.block.Block
import eu.mshade.enderframe.world.block.BlockTransformerController
import eu.mshade.enderframe.world.border.WorldBorder
import eu.mshade.enderframe.world.border.WorldBorderAction
import eu.mshade.enderframe.world.chunk.Chunk
import eu.mshade.enderframe.world.chunk.EmptySection
import eu.mshade.enderframe.world.chunk.Section
import eu.mshade.enderframe.wrapper.ContextWrapper
import eu.mshade.enderframe.wrapper.MaterialWrapperContext
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.enderman.`object`.EndermanObjectTransformerRepository
import eu.mshade.enderman.packet.login.MinecraftPacketOutEncryption
import eu.mshade.enderman.packet.login.MinecraftPacketOutLoginSuccess
import eu.mshade.enderman.packet.play.*
import eu.mshade.enderman.packet.play.entity.*
import eu.mshade.enderman.packet.play.inventory.*
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutDisplayScoreboard
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutScoreboardObjective
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutTeams
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutUpdateScoreboard
import eu.mshade.enderman.packet.play.world.MinecraftPacketOutServerDifficulty
import eu.mshade.enderman.wrapper.EndermanContextWrapper
import eu.mshade.enderman.wrapper.EndermanMaterialKeyWrapper
import io.netty.channel.Channel
import java.nio.ByteBuffer
import java.security.PublicKey
import java.util.*


class EndermanMinecraftSession(
    channel: Channel,
    minecraftProtocol: EndermanMinecraftProtocol
) : MinecraftSession(channel) {
    private val entityTypeWrapper: Wrapper<EntityKey?, Int?>?
    private val materialKeyWrapper: EndermanMaterialKeyWrapper?
    private val inventoryKeyWrapper: Wrapper<InventoryKey?, String?>?
    private val inventorySizeWrapper: Wrapper<InventoryKey?, Int?>?
    private val particleKeyWrapper: Wrapper<ParticleKey?, Int?>?
    private val attributeKeyWrapper: Wrapper<AttributeKey?, String?>?
    private val effectKeyWrapper: Wrapper<EffectKey?, Int?>?
    private val objectTransformerRepository: EndermanObjectTransformerRepository
    private val blockTransformerController: BlockTransformerController

    init {
        val wrapperRepository = minecraftProtocol.wrapperRepository
        objectTransformerRepository = minecraftProtocol.objectTransformerRepository
        blockTransformerController = minecraftProtocol.blockTransformerController


        materialKeyWrapper = wrapperRepository.get(ContextWrapper.MATERIAL_KEY) as EndermanMaterialKeyWrapper
        inventoryKeyWrapper =
            wrapperRepository.get(EndermanContextWrapper.INVENTORY_KEY) as Wrapper<InventoryKey?, String?>?
        inventorySizeWrapper =
            wrapperRepository.get(EndermanContextWrapper.INVENTORY_SIZE) as Wrapper<InventoryKey?, Int?>?
        entityTypeWrapper = wrapperRepository.get(EndermanContextWrapper.ENTITY_TYPE) as Wrapper<EntityKey?, Int?>?
        particleKeyWrapper = wrapperRepository.get(EndermanContextWrapper.PARTICLE_TYPE) as Wrapper<ParticleKey?, Int?>?

        attributeKeyWrapper =
            wrapperRepository.get(EndermanContextWrapper.ATTRIBUTE_KEY) as Wrapper<AttributeKey?, String?>?
        effectKeyWrapper = wrapperRepository.get(EndermanContextWrapper.EFFECT_TYPE) as Wrapper<EffectKey?, Int?>?
    }

    override fun sendCompression(threshold: Int) {
        sendPacket(MinecraftPacketOutSetCompression(threshold))
        enableCompression(threshold)
    }

    override fun sendLoginSuccess() {
        sendPacket(MinecraftPacketOutLoginSuccess(gameProfile))
        toggleProtocolStatus(MinecraftProtocolStatus.PLAY)
    }

    override fun sendJoinGame(world: World, reducedDebugInfo: Boolean) {
        val metadataKeyValueBucket = world.metadatas

        val dimension =
            metadataKeyValueBucket.getMetadataKeyValue(WorldMetadataType.DIMENSION).metadataValue as Dimension
        val difficulty =
            metadataKeyValueBucket.getMetadataKeyValue(WorldMetadataType.DIFFICULTY).metadataValue as Difficulty
        val player = MinecraftProtocolPipeline.get().getPlayer(channel)
        sendPacket(
            MinecraftPacketOutJoinGame(
                player.getEntityId(), player.gameMode, dimension, difficulty, 0, world.name, reducedDebugInfo
            )
        )
    }

    override fun sendServerDifficulty(difficulty: Difficulty) {
        sendPacket(MinecraftPacketOutServerDifficulty(difficulty))
    }

    override fun sendHeaderAndFooter(header: String, footer: String) {
        sendPacket(MinecraftPacketOutPlayerList(TextComponent.of(header), TextComponent.of(footer)))
    }

    override fun sendHeaderAndFooter(header: TextComponent, footer: TextComponent) {
        sendPacket(MinecraftPacketOutPlayerList(header, footer))
    }

    override fun sendAbilities(
        invulnerable: Boolean,
        flying: Boolean,
        allowFlying: Boolean,
        instantBreak: Boolean,
        flyingSpeed: Float,
        walkSpeed: Float
    ) {
        sendPacket(
            MinecraftPacketOutPlayerAbilities(
                invulnerable, flying, allowFlying, instantBreak, flyingSpeed, walkSpeed
            )
        )
    }

    override fun sendKeepAlive(threshold: Int) {
        sendPacket(MinecraftPacketOutKeepAlive(threshold))
    }

    override fun sendEncryption(publicKey: PublicKey) {
        sendPacket(MinecraftPacketOutEncryption(sessionId, publicKey, verifyToken))
    }

    override fun sendPlayerInfo(playerInfoBuilder: PlayerInfoBuilder) {
        sendPacket(MinecraftPacketOutPlayerInfo(playerInfoBuilder))
    }

    override fun sendMessage(textComponent: TextComponent, textPosition: TextPosition) {
        if (textPosition == TextPosition.HOT_BAR) {
            val s = textComponent.toLegacyText()
            sendPacket(MinecraftPacketOutChatMessage(TextComponent.of(s), TextPosition.HOT_BAR))
            return
        }
        sendPacket(MinecraftPacketOutChatMessage(textComponent, textPosition))
    }

    override fun sendMessage(textComponent: TextComponent) {
        sendMessage(textComponent, TextPosition.CHAT)
    }

    override fun sendMessage(message: String) {
        sendMessage(TextComponent.of(message))
    }

    override fun disconnect(message: String) {
        disconnect(TextComponent.of(message))
    }

    override fun disconnect(message: TextComponent) {
        sendPacket(MinecraftPacketOutDisconnect(message))
    }

    override fun teleport(location: Location) {
        sendPacket(
            MinecraftPacketOutPlayerPositionAndLook(
                location.x, location.y, location.z, location.yaw, location.pitch
            )
        )
    }

    override fun sendUpdateLocation(entity: Entity) {
        val now = entity.getLocation()
        val before = entity.getBeforeLocation()
        sendUpdateLocation(entity, before, now)
    }

    override fun sendUpdateLocation(entity: Entity, before: Location, now: Location) {
        val compareBodyLocation = now.compareBodyLocation(before)
        val compareHeadRotation = now.compareHeadRotation(before)
        if (!compareBodyLocation) {
            val teleport =
                (hasOverflow(floor(now.x * 32) - floor(before.x * 32)) || hasOverflow(floor(now.y * 32) - floor(before.y * 32)) || hasOverflow(
                    floor(now.z * 32) - floor(before.z * 32)
                ))
            if (!teleport) {
                if (!compareHeadRotation) this.sendMoveAndLook(entity, before, now) else this.sendMove(
                    entity, before, now
                )
            } else {
                this.sendTeleport(entity, now)
            }
        }
        if (!compareHeadRotation) {
            this.sendLook(entity, now)
            this.sendHeadLook(entity, now)
        }
    }

    override fun sendTeleport(entity: Entity) {
        sendTeleport(entity, entity.getLocation())
    }

    override fun sendTeleport(entity: Entity, location: Location) {
        val x = floor(entity.getLocation().x * 32)
        val y = floor(entity.getLocation().y * 32)
        val z = floor(entity.getLocation().z * 32)
        val yaw = (entity.getLocation().yaw * 256.0f / 360.0f).toInt()
        val pitch = (entity.getLocation().pitch * 256.0f / 360.0f).toInt()
        sendPacket(
            MinecraftPacketOutEntityTeleport(
                entity,
                x,
                y,
                z,
                yaw,
                pitch,
                false
            )
        )
    }

    override fun sendMove(entity: Entity) {
        val now = entity.getLocation()
        val before = entity.getBeforeLocation()
        sendMove(entity, before, now)
    }

    override fun sendMove(entity: Entity, before: Location, now: Location) {
        val x = (floor(now.x * 32) - floor(before.x * 32)).toByte()
        val y = (floor(now.y * 32) - floor(before.y * 32)).toByte()
        val z = (floor(now.z * 32) - floor(before.z * 32)).toByte()
        sendPacket(
            MinecraftPacketOutEntityRelativeMove(
                entity,
                x,
                y,
                z
            )
        )
    }

    override fun sendMoveAndLook(entity: Entity) {
        val now = entity.getLocation()
        val before = entity.getBeforeLocation()
        sendMoveAndLook(entity, before, now)
    }

    override fun sendMoveAndLook(entity: Entity, before: Location, now: Location) {
        val x = (floor(now.x * 32) - floor(before.x * 32)).toByte()
        val y = (floor(now.y * 32) - floor(before.y * 32)).toByte()
        val z = (floor(now.z * 32) - floor(before.z * 32)).toByte()
        val yaw = (now.yaw * 256 / 360).toInt()
        val pitch = (now.pitch * 256 / 360).toInt()
        sendPacket(
            MinecraftPacketOutEntityLookRelativeMove(
                entity,
                x,
                y,
                z,
                yaw,
                pitch
            )
        )
    }

    override fun sendLook(entity: Entity) {
        sendLook(entity, entity.getLocation())
    }

    override fun sendLook(entity: Entity, location: Location) {
        sendPacket(
            MinecraftPacketOutEntityLook(
                entity,
                location
            )
        )
    }

    override fun sendHeadLook(entity: Entity) {
        sendHeadLook(entity, entity.getLocation())
    }

    override fun sendEquipment(entity: Entity, equipmentSlot: EquipmentSlot, itemStack: ItemStack?) {
        if (equipmentSlot == EquipmentSlot.OFF_HAND) return
        var slot = equipmentSlot.ordinal
        if (equipmentSlot.ordinal > 0) {
            slot = equipmentSlot.ordinal - 1
        }
        sendPacket(MinecraftPacketOutEntityEquipment(entity, slot, itemStack))
    }

    override fun sendHeadLook(entity: Entity, location: Location) {
        sendPacket(
            MinecraftPacketOutEntityHeadLook(
                entity,
                location
            )
        )
    }

    override fun sendEntity(vararg entities: Entity) {
        for (entity in entities) {
            if (entity is Player) {
                sendPacket(MinecraftPacketOutSpawnPlayer(entity))
            } else {
                val id = entityTypeWrapper!!.map(entity.entityKey) ?: continue
                val category: EntityCategory = protocol.entityMapper.getCategory(entity.entityKey) ?: continue
                if (category === EntityCategory.ENTITY) {
                    sendPacket(MinecraftPacketOutSpawnEntity(id, entity))
                } else {
                    val data: Int = objectTransformerRepository.transform(entity)
                    sendPacket(MinecraftPacketOutSpawnObject(id, entity, data))
                    this.sendMetadata(entity, entity.metadata.metadataKeys)
                }
            }
            this.sendTeleport(entity)
        }
    }

    override fun removeEntity(vararg entities: Entity) {
        sendPacket(MinecraftPacketOutDestroyEntities(*entities))
    }

    override fun sendMetadata(entity: Entity, vararg entityMetadataKeys: MetadataKey) {
        sendMetadata(entity, entityMetadataKeys.toList())
    }

    override fun sendMetadata(entity: Entity, entityMetadataKeys: Collection<MetadataKey>) {
        sendPacket(
            MinecraftPacketOutEntityMetadata(
                entity,
                entityMetadataKeys
            )
        )
    }

    override fun sendEntityProperties(entity: Entity){

        val properties = mutableListOf<EndermanAttributeProperty>()

        for (property in entity.getProperties()) {
            val attribute = attributeKeyWrapper?.map(property.attribute) ?: continue
            properties.add(EndermanAttributeProperty(attribute, property.value, property.getAttributeModifiers()))
        }

        sendPacket(MinecraftPacketOutEntityProperties(entity, properties))
    }

    override fun sendEntityEffect(entity: Entity, vararg effects: Effect) {
        for (effect in effects) {
            val type = effectKeyWrapper?.map(effect.type) ?: continue
            sendPacket(MinecraftPacketOutEntityEffect(entity, EndermanEffect(type, effect.amplifier, effect.duration, effect.particle)))
        }
    }

    override fun sendRemoveEntityEffect(entity: Entity, vararg effectTypes: EffectKey){
        for (effectType in effectTypes) {
            val type = effectKeyWrapper?.map(effectType) ?: continue
            sendPacket(MinecraftPacketOutRemoveEntityEffect(entity, type))
        }
    }


    override fun sendChunk(chunk: Chunk) {
        val sections: MutableList<Section> = ArrayList()
        var bitMask = chunk.bitMask
        for (sectionBuffer in chunk.sections) {
            if (sectionBuffer != null && sectionBuffer.realBlock != 0) {
                sections.add(sectionBuffer)
            }
        }
        if (sections.size == 0) {
            sections.add(EmptySection(chunk, 0))
            bitMask = 1
        }
        val overWorld = chunk.world.dimension == Dimension.OVERWORLD
        val capacity = sections.size * (4096 * 2 + if (overWorld) 2048 * 2 else 2048) + 256
        val byteBuffer = ByteBuffer.allocate(capacity)
        for (section in sections) {
            val palette = section.palette
            val wrappedPalette: MutableMap<Int, MaterialKey> = HashMap()
            for (paletteEntry in palette.getBlocks()) {
                blockTransformerController.transform(paletteEntry.block)?.let {
                    wrappedPalette[paletteEntry.id] = it
                }
            }
            for (i in 0..4095) {
                var materialKey = wrappedPalette[section.blocks[i]]
                if (materialKey == null) {
                    println(
                        "Cannot find material key for " + section.blocks[i] + " in palette " + palette.getBlock(
                            section.blocks[i]
                        ) + " in section " + section
                    )
                    materialKey = MaterialKey.from(95, 15)
                }
                byteBuffer.put((materialKey!!.id shl 4 or materialKey.metadata).toByte())
                byteBuffer.put((materialKey.id shr 4).toByte())
            }
        }
        for (section in sections) {
            byteBuffer.put(section.blockLight.rawData)
        }
        if (overWorld) {
            for (section in sections) {
                byteBuffer.put(section.skyLight.rawData)
            }
        }
        byteBuffer.put(chunk.biomes)
        sendPacket(MinecraftPacketOutChunkData(chunk.x, chunk.z, true, bitMask, byteBuffer.array()))
    }

    override fun sendSection(section: Section) {
        val chunk = section.chunk
        val overWorld = chunk.world.dimension == Dimension.OVERWORLD
        val capacity = 4096 * 2 + if (overWorld) 2048 * 2 else 2048
        val byteBuffer = ByteBuffer.allocate(capacity)
        val palette = section.palette
        val wrappedPalette: MutableMap<Int, MaterialKey> = HashMap()
        for (paletteEntry in palette.getBlocks()) {
            blockTransformerController.transform(paletteEntry.block)?.let {
                wrappedPalette[paletteEntry.id] = it
            }
        }
        for (i in 0..4095) {
            var materialKey = wrappedPalette[section.blocks[i]]
            if (materialKey == null) {
                println(
                    "Cannot find material key for " + section.blocks[i] + " in palette " + palette.getBlock(
                        section.blocks[i]
                    ) + " in section " + section
                )
                materialKey = MaterialKey.from(95, 14)
            }
            byteBuffer.put((materialKey!!.id shl 4 or materialKey.metadata).toByte())
            byteBuffer.put((materialKey.id shr 4).toByte())
        }
        byteBuffer.put(section.blockLight.rawData)
        if (overWorld) {
            byteBuffer.put(section.skyLight.rawData)
        }
        sendPacket(MinecraftPacketOutChunkData(chunk.x, chunk.z, false, 1 shl section.y, byteBuffer.array()))

    }

    override fun sendSectionFromChunk(chunk: Chunk) {
        val sections: MutableList<Section> = ArrayList()
        var bitMask = chunk.bitMask
        for (sectionBuffer in chunk.sections) {
            if (sectionBuffer != null && sectionBuffer.realBlock != 0) {
                sections.add(sectionBuffer)
            }
        }
        if (sections.size == 0) {
            sections.add(EmptySection(chunk, 0))
            bitMask = 1
        }
        val overWorld = chunk.world.dimension == Dimension.OVERWORLD
        val capacity = sections.size * (4096 * if (overWorld) 4 else 2) + 256
        val byteBuffer = ByteBuffer.allocate(capacity)
        for (section in sections) {
            val palette = section.palette
            val wrappedPalette: MutableMap<Int, MaterialKey> = HashMap()
            for (paletteEntry in palette.getBlocks()) {
                blockTransformerController.transform(paletteEntry.block)?.let {
                    wrappedPalette[paletteEntry.id] = it
                }
            }
            for (i in 0..4095) {
                var materialKey = wrappedPalette[section.blocks[i]]
                if (materialKey == null) {
                    materialKey = MaterialKey.from(95, 14)
                }
                byteBuffer.put((materialKey!!.id shl 4 or materialKey!!.metadata).toByte())
                byteBuffer.put((materialKey!!.id shr 4).toByte())
            }
        }
        for (section in sections) {
            byteBuffer.put(section.blockLight.rawData)
        }
        if (overWorld) {
            for (section in sections) {
                byteBuffer.put(section.skyLight.rawData)
            }
        }
        sendPacket(MinecraftPacketOutChunkData(chunk.x, chunk.z, false, bitMask, byteBuffer.array()))
    }

    override fun sendUnloadChunk(chunk: Chunk) {
        sendPacket(MinecraftPacketOutChunkData(chunk.x, chunk.z, true, 0, ByteArray(0)))
    }

    override fun sendBlockChange(blockPosition: Vector, materialKey: MaterialKey) {
        sendPacket(MinecraftPacketOutBlockChange(blockPosition, materialKeyWrapper!!.map(MaterialWrapperContext.BLOCK, materialKey)))
    }

    override fun sendBlockChange(blockPosition: Vector, block: Block) {
        sendPacket(MinecraftPacketOutBlockChange(blockPosition, blockTransformerController.transform(block)))
    }

    override fun sendUnsafeBlockChange(blockPosition: Vector, materialKey: MaterialKey) {
        sendPacket(MinecraftPacketOutBlockChange(blockPosition, materialKey))
    }

    override fun sendSign(vector: Vector, textComponents: List<TextComponent>) {
        sendPacket(MinecraftPacketOutUpdateSign(vector, textComponents))
    }

    override fun sendOpenInventory(inventory: NamedInventory) {
        val inventoryKey = inventoryKeyWrapper!!.map(inventory.inventoryKey) ?: return
        var size = inventorySizeWrapper!!.map(inventory.inventoryKey) ?: return
        MinecraftProtocolPipeline.get().getPlayer(channel).openedInventory = inventory
        if (inventory.inventoryKey == InventoryType.CHEST) {
            size = inventory.size
        }

        /*
    int freeId = this.uniqueIdManager.getFreeId();
    this.inventoryRepository.register(freeId, inventory);

     */sendPacket(MinecraftPacketOutOpenInventory(inventoryKey, size, 1, inventory))
    }

    override fun sendCloseInventory(inventory: Inventory) {
        var id = 0
        if (inventory is NamedInventory) {
            id = 1
        }
        sendPacket(MinecraftPacketOutCloseInventory(id))
    }

    override fun sendItemStacks(inventory: Inventory) {
        val id: Int
        id = if (inventory is PlayerInventory) {
            0
        } else {
            1
            //id = this.inventoryRepository.getIdOfInventory(inventory);
        }
        sendPacket(MinecraftPacketOutInventoryItems(id, inventory))
    }


    override fun sendItemStack(inventory: Inventory, slot: Int, itemStack: ItemStack?) {
        var id = 0
        if (inventory !is PlayerInventory) {
            id = 1
        }

        println("Sending itemstack $itemStack")
        sendPacket(MinecraftPacketOutSetItemStack(slot, id, itemStack))
    }

    override fun sendDisplayScoreboard(scoreboard: Scoreboard) {
        sendPacket(MinecraftPacketOutDisplayScoreboard(scoreboard))
    }

    override fun sendScoreboard(scoreboard: Scoreboard, mode: ScoreboardAction) {
        sendPacket(MinecraftPacketOutScoreboardObjective(scoreboard, mode))
    }

    override fun sendUpdateScoreboardLine(
        scoreboard: Scoreboard, scoreboardLine: ScoreboardLine, scoreboardLineAction: ScoreboardLineAction
    ) {
        sendPacket(MinecraftPacketOutUpdateScoreboard(scoreboard, scoreboardLine, scoreboardLineAction))
    }

    override fun sendTeams(team: Team) {
        sendPacket(MinecraftPacketOutTeams(team))
    }

    override fun sendSoundEffect(soundEffect: SoundEffect) {
        sendPacket(MinecraftPacketOutSoundEffect(soundEffect))
    }

    override fun sendTitle(titleAction: TitleAction, title: Title) {
        sendPacket(MinecraftPacketOutTitle(titleAction, title))
    }

    override fun sendWorldBorder(worldBorderAction: WorldBorderAction, worldBorder: WorldBorder) {
        sendPacket(MinecraftPacketOutWorldBorder(worldBorderAction, worldBorder))
    }

    override fun sendParticle(particle: Particle) {
        sendPacket(MinecraftPacketOutParticle(materialKeyWrapper, particleKeyWrapper, particle))
    }

    override fun sendInventoryUpdate(block: Block, vararg metadataKeys: MetadataKey) {
        TODO("Not yet implemented")
    }

    private fun hasOverflow(value: Int): Boolean {
        return value > 3 || value < -3
    }

    private fun floor(d0: Double): Int {
        val i = d0.toInt()
        return if (d0 < i.toDouble()) i - 1 else i
    }

}