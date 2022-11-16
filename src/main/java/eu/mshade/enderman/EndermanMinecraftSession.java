package eu.mshade.enderman;

import eu.mshade.enderframe.PlayerInfoBuilder;
import eu.mshade.enderframe.UniqueId;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.inventory.ChestInventory;
import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.inventory.InventoryKey;
import eu.mshade.enderframe.inventory.PlayerInventory;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.particle.Particle;
import eu.mshade.enderframe.particle.ParticleKey;
import eu.mshade.enderframe.protocol.MinecraftProtocolPipeline;
import eu.mshade.enderframe.protocol.MinecraftProtocolStatus;
import eu.mshade.enderframe.protocol.MinecraftSession;
import eu.mshade.enderframe.protocol.packet.*;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardMode;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjective;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjectiveAction;
import eu.mshade.enderframe.scoreboard.team.Team;
import eu.mshade.enderframe.sound.SoundEffect;
import eu.mshade.enderframe.title.Title;
import eu.mshade.enderframe.title.TitleAction;
import eu.mshade.enderframe.world.*;
import eu.mshade.enderframe.world.block.Block;
import eu.mshade.enderframe.world.block.BlockTransformerRepository;
import eu.mshade.enderframe.world.border.WorldBorder;
import eu.mshade.enderframe.world.border.WorldBorderAction;
import eu.mshade.enderframe.world.chunk.Chunk;
import eu.mshade.enderframe.world.chunk.EmptySection;
import eu.mshade.enderframe.world.chunk.Palette;
import eu.mshade.enderframe.world.chunk.Section;
import eu.mshade.enderframe.wrapper.ContextWrapper;
import eu.mshade.enderframe.wrapper.Wrapper;
import eu.mshade.enderframe.wrapper.WrapperRepository;
import eu.mshade.enderman.packet.login.MinecraftPacketOutEncryption;
import eu.mshade.enderman.packet.login.MinecraftPacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.*;
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketOutCloseInventory;
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketOutInventoryItems;
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketOutOpenInventory;
import eu.mshade.enderman.packet.play.inventory.MinecraftPacketOutSetItemStack;
import eu.mshade.enderman.packet.play.MinecraftPacketOutPlayerPositionAndLook;
import eu.mshade.enderman.wrapper.EndermanContextWrapper;
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutDisplayScoreboard;
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutScoreboardObjective;
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutTeams;
import eu.mshade.enderman.packet.play.scoreboard.MinecraftPacketOutUpdateScoreboard;
import io.netty.channel.Channel;

import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndermanMinecraftSession extends MinecraftSession {


    private Wrapper<EntityType, Integer> entityTypeWrapper;
    private Wrapper<MaterialKey, MaterialKey> materialKeyWrapper;
    private Wrapper<InventoryKey, String> inventoryKeyWrapper;
    private Wrapper<InventoryKey, Integer> inventorySizeWrapper;
    private Wrapper<ParticleKey, Integer> particleKeyWrapper;
    private BlockTransformerRepository blockTransformerRepository;
    private UniqueId uniqueId = new UniqueId();

    public EndermanMinecraftSession(Channel channel, WrapperRepository wrapperRepository, BlockTransformerRepository blockTransformerRepository) {
        super(channel);
        this.blockTransformerRepository = blockTransformerRepository;
        this.materialKeyWrapper = (Wrapper<MaterialKey, MaterialKey>) wrapperRepository.get(ContextWrapper.MATERIAL_KEY);
        this.inventoryKeyWrapper = (Wrapper<InventoryKey, String>) wrapperRepository.get(EndermanContextWrapper.INVENTORY_KEY);
        this.inventorySizeWrapper = (Wrapper<InventoryKey, Integer>) wrapperRepository.get(EndermanContextWrapper.INVENTORY_SIZE);
        this.entityTypeWrapper = (Wrapper<EntityType, Integer>) wrapperRepository.get(EndermanContextWrapper.ENTITY_TYPE);
        this.particleKeyWrapper = (Wrapper<ParticleKey, Integer>) wrapperRepository.get(EndermanContextWrapper.PARTICLE_TYPE);
    }

    @Override
    public void sendCompression(int threshold) {
        this.sendPacket(new MinecraftPacketOutSetCompression(threshold));
        this.enableCompression(threshold);
    }

    @Override
    public void sendLoginSuccess() {
        this.sendPacket(new MinecraftPacketOutLoginSuccess(this.getGameProfile()));
        this.toggleProtocolStatus(MinecraftProtocolStatus.PLAY);
    }

    @Override
    public void sendJoinGame(World world, boolean reducedDebugInfo) {
        MetadataKeyValueBucket metadataKeyValueBucket = world.getMetadataKeyValueBucket();

        Dimension dimension = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIMENSION, Dimension.class);
        Difficulty difficulty = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIFFICULTY, Difficulty.class);

        Player player = MinecraftProtocolPipeline.get().getPlayer(this.getChannel());
        sendPacket(new MinecraftPacketOutJoinGame(player.getEntityId(), player.getGameMode(), dimension, difficulty, 0, world.getName(), reducedDebugInfo));
    }

    @Override
    public void sendHeaderAndFooter(String header, String footer) {
        sendPacket(new MinecraftPacketOutPlayerList(TextComponent.of(header), TextComponent.of(footer)));
    }

    @Override
    public void sendHeaderAndFooter(TextComponent header, TextComponent footer) {
        sendPacket(new MinecraftPacketOutPlayerList(header, footer));
    }

    @Override
    public void sendAbilities(boolean invulnerable, boolean flying, boolean allowFlying, boolean instantBreak, float flyingSpeed, float walkSpeed) {
        sendPacket(new MinecraftPacketOutPlayerAbilities(invulnerable, flying, allowFlying, instantBreak, flyingSpeed, walkSpeed));
    }

    @Override
    public void sendKeepAlive(int threshold) {
        sendPacket(new MinecraftPacketOutKeepAlive(threshold));
    }

    @Override
    public void sendEncryption(PublicKey publicKey) {
        sendPacket(new MinecraftPacketOutEncryption(this.getSessionId(), publicKey, this.getVerifyToken()));
    }

    @Override
    public void sendPlayerInfo(PlayerInfoBuilder playerInfoBuilder) {
        sendPacket(new MinecraftPacketOutPlayerInfo(playerInfoBuilder));
    }

    @Override
    public void sendMessage(TextComponent textComponent, TextPosition textPosition) {
        if (textPosition == TextPosition.HOT_BAR){
            String s = textComponent.toLegacyText();
            sendPacket(new MinecraftPacketOutChatMessage(TextComponent.of(s), TextPosition.HOT_BAR));
            return;
        }
        sendPacket(new MinecraftPacketOutChatMessage(textComponent, textPosition));
    }

    @Override
    public void sendMessage(TextComponent textComponent) {
        sendMessage(textComponent, TextPosition.CHAT);
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(TextComponent.of(message));
    }

    @Override
    public void disconnect(String message) {
        disconnect(TextComponent.of(message));
    }

    @Override
    public void disconnect(TextComponent message) {
        sendPacket(new MinecraftPacketOutDisconnect(message));
    }

    @Override
    public void teleport(Location location) {
        sendPacket(new MinecraftPacketOutPlayerPositionAndLook(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }

    @Override
    public void sendUpdateLocation(Entity entity) {
        Location now = entity.getLocation();
        Location before = entity.getBeforeLocation();
        sendUpdateLocation(entity, before, now);

    }

    @Override
    public void sendUpdateLocation(Entity entity, Location before, Location now) {
        boolean compareBodyLocation = now.compareBodyLocation(before);
        boolean compareHeadRotation = now.compareHeadRotation(before);

        if (!compareBodyLocation) {
            boolean teleport = hasOverflow(floor(now.getX() * 32) - floor(before.getX() * 32))
                    || hasOverflow(floor(now.getY() * 32) - floor(before.getY() * 32))
                    || hasOverflow(floor(now.getZ() * 32) - floor(before.getZ() * 32));
            if (!teleport) {
                if(!compareHeadRotation) this.sendMoveAndLook(entity, before, now);
                else this.sendMove(entity, before, now);
            } else {
                this.sendTeleport(entity, now);
            }
        }


        if(!compareHeadRotation) {
            this.sendLook(entity, now);
            this.sendHeadLook(entity, now);
        }

    }

    @Override
    public void sendTeleport(Entity entity) {
        sendTeleport(entity, entity.getLocation());
    }

    @Override
    public void sendTeleport(Entity entity, Location location) {
        int x = floor(entity.getLocation().getX() *32);
        int y = floor(entity.getLocation().getY() *32);
        int z = floor(entity.getLocation().getZ() *32);
        int yaw = (int) (entity.getLocation().getYaw() * 256.0F / 360.0F);
        int pitch = (int) (entity.getLocation().getPitch() * 256.0F / 360.0F);
        this.sendPacket(new MinecraftPacketOutEntityTeleport(entity, x, y, z, yaw, pitch, false));
    }

    @Override
    public void sendMove(Entity entity) {
        Location now = entity.getLocation();
        Location before = entity.getBeforeLocation();
        sendMove(entity, before, now);
    }

    @Override
    public void sendMove(Entity entity, Location before, Location now) {
        byte x = (byte) (floor(now.getX() * 32) - floor(before.getX() * 32));
        byte y = (byte) (floor(now.getY() * 32) - floor(before.getY() * 32));
        byte z = (byte) (floor(now.getZ() * 32) - floor(before.getZ() * 32));

        this.sendPacket(new MinecraftPacketOutEntityRelativeMove(entity, x, y, z));
    }

    @Override
    public void sendMoveAndLook(Entity entity) {
        Location now = entity.getLocation();
        Location before = entity.getBeforeLocation();

        sendMoveAndLook(entity, before, now);
    }

    @Override
    public void sendMoveAndLook(Entity entity, Location before, Location now) {
        byte x = (byte) (floor(now.getX() * 32) - floor(before.getX() * 32));
        byte y = (byte) (floor(now.getY() * 32) - floor(before.getY() * 32));
        byte z = (byte) (floor(now.getZ() * 32) - floor(before.getZ() * 32));

        int yaw = (int) (now.getYaw() * 256 / 360);
        int pitch = (int) (now.getPitch() * 256 / 360);

        this.sendPacket(new MinecraftPacketOutEntityLookRelativeMove(entity, x, y, z, yaw, pitch));
    }

    @Override
    public void sendLook(Entity entity) {
        sendLook(entity, entity.getLocation());
    }

    @Override
    public void sendLook(Entity entity, Location location) {
        this.sendPacket(new MinecraftPacketOutEntityLook(entity, location));
    }


    @Override
    public void sendHeadLook(Entity entity) {
        sendHeadLook(entity, entity.getLocation());
    }

    @Override
    public void sendHeadLook(Entity entity, Location location) {
        this.sendPacket(new MinecraftPacketOutEntityHeadLook(entity, location));
    }

    @Override
    public void sendEntity(Entity... entities) {
        for (Entity entity : entities) {
            if(entity instanceof Player) {
                this.sendPacket(new MinecraftPacketOutSpawnPlayer((Player)entity));
            } else {
                Integer id = entityTypeWrapper.wrap(entity.getEntityType());
                if (id == null) continue;
                this.sendPacket(new MinecraftPacketOutSpawnMob(id, entity));
            }
            this.sendTeleport(entity);

        }
    }

    @Override
    public void removeEntity(Entity... entities) {
        this.sendPacket(new MinecraftPacketOutDestroyEntities(entities));
    }

    @Override
    public void sendMetadata(Entity entity, EntityMetadataKey... entityMetadataKeys) {
        sendPacket(new MinecraftPacketOutEntityMetadata(entity, entityMetadataKeys));
    }

    @Override
    public void sendChunk(Chunk chunk) {
        List<Section> sections = new ArrayList<>();
        int bitMask = chunk.getBitMask();
        for (Section sectionBuffer : chunk.getSections()) {
            if (sectionBuffer != null && sectionBuffer.getRealBlock() != 0) {
                sections.add(sectionBuffer);
            }
        }

        if (sections.size() == 0) {
            sections.add(new EmptySection(chunk,0));
            bitMask = 1;
        }

        boolean overWorld = chunk.getWorld().getDimension() == Dimension.OVERWORLD;

        int capacity = sections.size() * (4096 * 2 + (overWorld ? 2048 * 2 : 2048)) + 256;

        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        for (Section section : sections) {

            Palette palette = section.getPalette();
            Map<Integer, MaterialKey> wrappedPalette = new HashMap<>();
            for (Map.Entry<Integer, Block> entry : palette.getBlockById().entrySet()) {
                wrappedPalette.put(entry.getKey(), blockTransformerRepository.transform(entry.getValue()));
            }
            for (int i = 0; i < 4096; i++) {
                MaterialKey materialKey = wrappedPalette.get(section.getBlocks()[i]);
                if (materialKey == null) {
                    System.out.println("Cannot find material key for " + section.getBlocks()[i]+" in palette "+palette.getBlock(section.getBlocks()[i])+ " in section "+section);
                    materialKey = MaterialKey.from(95, 15);
                }
                byteBuffer.put((byte) (materialKey.getId() << 4 | materialKey.getMetadata()));
                byteBuffer.put((byte) (materialKey.getId() >> 4));
            }
        }

        for (Section section : sections) {
            byteBuffer.put(section.getBlockLight().getRawData());
        }

        if (overWorld) {
            for (Section section : sections) {
                byteBuffer.put(section.getSkyLight().getRawData());
            }
        }

        byteBuffer.put(chunk.getBiomes());

        sendPacket(new MinecraftPacketOutChunkData(chunk.getX(), chunk.getZ(), true, bitMask, byteBuffer.array()));
    }

    @Override
    public void sendSection(Section section) {
        Chunk chunk = section.getChunk();
        boolean overWorld = chunk.getWorld().getDimension() == Dimension.OVERWORLD;
        int capacity =  4096 * 2 + (overWorld ? 2048 * 2 : 2048);
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        Palette palette = section.getPalette();
        Map<Integer, MaterialKey> wrappedPalette = new HashMap<>();
        for (Map.Entry<Integer, Block> entry : palette.getBlockById().entrySet()) {
            wrappedPalette.put(entry.getKey(), blockTransformerRepository.transform(entry.getValue()));
        }

        for (int i = 0; i < 4096; i++) {
            MaterialKey materialKey = wrappedPalette.get(section.getBlocks()[i]);
            if (materialKey == null) {
                System.out.println("Cannot find material key for " + section.getBlocks()[i]+" in palette "+palette.getBlock(section.getBlocks()[i])+ " in section "+section);
                materialKey = MaterialKey.from(95, 14);
            }
            byteBuffer.put((byte) (materialKey.getId() << 4 | materialKey.getMetadata()));
            byteBuffer.put((byte) (materialKey.getId() >> 4));
        }

        byteBuffer.put(section.getBlockLight().getRawData());
        if (overWorld) {
            byteBuffer.put(section.getSkyLight().getRawData());
        }
        sendPacket(new MinecraftPacketOutChunkData(chunk.getX(), chunk.getZ(), false, 1 << section.getY(), byteBuffer.array()));
    }

    @Override
    public void sendSectionFromChunk(Chunk chunk){
        List<Section> sections = new ArrayList<>();
        int bitMask = chunk.getBitMask();
        for (Section sectionBuffer : chunk.getSections()) {
            if (sectionBuffer != null && sectionBuffer.getRealBlock() != 0) {
                sections.add(sectionBuffer);
            }
        }

        if (sections.size() == 0) {
            sections.add(new EmptySection(chunk,0));
            bitMask = 1;
        }

        boolean overWorld = chunk.getWorld().getDimension() == Dimension.OVERWORLD;

        int capacity = sections.size() * (4096 * (overWorld ? 4 : 2)) + 256;

        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        for (Section section : sections) {

            Palette palette = section.getPalette();
            Map<Integer, MaterialKey> wrappedPalette = new HashMap<>();
            for (Map.Entry<Integer, Block> entry : palette.getBlockById().entrySet()) {
                wrappedPalette.put(entry.getKey(), blockTransformerRepository.transform(entry.getValue()));
            }

            for (int i = 0; i < 4096; i++) {
                MaterialKey materialKey = wrappedPalette.get(section.getBlocks()[i]);
                if (materialKey == null) {
                    materialKey = MaterialKey.from(95, 14);
                }
                byteBuffer.put((byte) (materialKey.getId() << 4 | materialKey.getMetadata()));
                byteBuffer.put((byte) (materialKey.getId() >> 4));
            }
        }

        for (Section section : sections) {
            byteBuffer.put(section.getBlockLight().getRawData());
        }

        if (overWorld) {
            for (Section section : sections) {
                byteBuffer.put(section.getSkyLight().getRawData());
            }
        }


        sendPacket(new MinecraftPacketOutChunkData(chunk.getX(), chunk.getZ(), false, bitMask, byteBuffer.array()));
    }

    @Override
    public void sendUnloadChunk(Chunk chunk) {
        sendPacket(new MinecraftPacketOutChunkData(chunk.getX(), chunk.getZ(), true, 0, new byte[0]));
    }

    @Override
    public void sendBlockChange(Vector blockPosition, MaterialKey materialKey) {
        sendPacket(new MinecraftPacketOutBlockChange(blockPosition, materialKeyWrapper.wrap(materialKey)));
    }


    @Override
    public void sendBlockChange(Vector blockPosition, Block block) {

        sendPacket(new MinecraftPacketOutBlockChange(blockPosition, blockTransformerRepository.transform(block)));
    }

    @Override
    public void sendUnsafeBlockChange(Vector blockPosition, MaterialKey materialKey) {
        sendPacket(new MinecraftPacketOutBlockChange(blockPosition, materialKey));
    }

    @Override
    public void sendSign(Vector vector, List<TextComponent> textComponents) {
        sendPacket(new MinecraftPacketOutUpdateSign(vector, textComponents));
    }


    @Override
    public void sendOpenInventory(Inventory inventory) {
        if (inventory instanceof PlayerInventory) return;

        String inventoryKey = inventoryKeyWrapper.wrap(inventory.getInventoryKey());
        if (inventoryKey == null) return;
        Integer size = inventorySizeWrapper.wrap(inventory.getInventoryKey());
        if (size == null) return;

        MinecraftProtocolPipeline.get().getPlayer(channel).setOpenedInventory(inventory);

        if (inventory instanceof ChestInventory) {
            size = inventory.getSize();
        }

        /*
        int freeId = this.uniqueIdManager.getFreeId();
        this.inventoryRepository.register(freeId, inventory);

         */
        sendPacket(new MinecraftPacketOutOpenInventory(inventoryKey, size, 1, inventory));
    }

    @Override
    public void sendCloseInventory(Inventory inventory) {
        int id = this.inventoryRepository.getIdOfInventory(inventory);
        sendPacket(new MinecraftPacketOutCloseInventory(id));
        this.inventoryRepository.flush(inventory);
    }

    @Override
    public void sendItemStacks(Inventory inventory) {

        int id;

        if (inventory instanceof PlayerInventory){
            id = 0;
        } else {
            id = 1;
            //id = this.inventoryRepository.getIdOfInventory(inventory);
        }



        sendPacket(new MinecraftPacketOutInventoryItems(id, inventory));
    }

    @Override
    public void sendItemStack(Inventory inventory, int slot, ItemStack itemStack) {

        int id;
        if (inventory instanceof PlayerInventory){
            id = 0;
            slot = PlayerInventory.accurateSlot(slot);
        } else {
            id = 1;
            //id = this.inventoryRepository.getIdOfInventory(inventory);
        }
        sendPacket(new MinecraftPacketOutSetItemStack(slot, id, itemStack));
    }

    @Override
    public void sendDisplayScoreboard(Scoreboard<?> scoreboard) {
        sendPacket(new MinecraftPacketOutDisplayScoreboard(scoreboard));
    }

    @Override
    public void sendScoreboardObjective(Scoreboard<?> scoreboard, ScoreboardMode scoreboardMode) {
        sendPacket(new MinecraftPacketOutScoreboardObjective(scoreboard, scoreboardMode));
    }

    @Override
    public void sendUpdateScoreboard(ScoreboardObjective<?> scoreboardObjective, ScoreboardObjectiveAction scoreboardObjectiveAction) {
        sendPacket(new MinecraftPacketOutUpdateScoreboard(scoreboardObjective, scoreboardObjectiveAction));
    }

    @Override
    public void sendTeams(Team team) {
        sendPacket(new MinecraftPacketOutTeams(team));
    }

    @Override
    public void sendSoundEffect(SoundEffect soundEffect) {
        sendPacket(new MinecraftPacketOutSoundEffect(soundEffect));
    }

    @Override
    public void sendTitle(TitleAction titleAction, Title title) {
        sendPacket(new MinecraftPacketOutTitle(titleAction, title));
    }

    @Override
    public void sendWorldBorder(WorldBorderAction worldBorderAction, WorldBorder worldBorder) {
        sendPacket(new MinecraftPacketOutWorldBorder(worldBorderAction, worldBorder));
    }

    @Override
    public void sendParticle(Particle particle) {
        sendPacket(new MinecraftPacketOutParticle(materialKeyWrapper, particleKeyWrapper, particle));
    }

    private boolean hasOverflow(int value) {
        return value > 3 || value < -3;
    }

    private int floor(double d0) {
        int i = (int) d0;

        return d0 < (double) i ? i - 1 : i;
    }
}
