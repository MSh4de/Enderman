package eu.mshade.enderman;

import eu.mshade.enderframe.PlayerInfoBuilder;
import eu.mshade.enderframe.UniqueIdManager;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.entity.EntityRepository;
import eu.mshade.enderframe.entity.Player;
import eu.mshade.enderframe.inventory.Inventory;
import eu.mshade.enderframe.inventory.PlayerInventory;
import eu.mshade.enderframe.item.ItemStack;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.metadata.MetadataKeyValueBucket;
import eu.mshade.enderframe.metadata.entity.EntityMetadataKey;
import eu.mshade.enderframe.metadata.world.WorldMetadataType;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.mojang.chat.TextPosition;
import eu.mshade.enderframe.protocol.ProtocolPipeline;
import eu.mshade.enderframe.protocol.ProtocolStatus;
import eu.mshade.enderframe.protocol.SessionWrapper;
import eu.mshade.enderframe.protocol.packet.*;
import eu.mshade.enderframe.scoreboard.Scoreboard;
import eu.mshade.enderframe.scoreboard.ScoreboardMode;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjective;
import eu.mshade.enderframe.scoreboard.objective.ScoreboardObjectiveAction;
import eu.mshade.enderframe.sound.SoundEffect;
import eu.mshade.enderframe.title.Title;
import eu.mshade.enderframe.title.TitleAction;
import eu.mshade.enderframe.world.*;
import eu.mshade.enderman.packet.login.PacketOutEncryption;
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.*;
import eu.mshade.enderman.packet.play.inventory.PacketOutCloseInventory;
import eu.mshade.enderman.packet.play.inventory.PacketOutInventoryItems;
import eu.mshade.enderman.packet.play.inventory.PacketOutOpenInventory;
import eu.mshade.enderman.packet.play.inventory.PacketOutSetItemStack;
import eu.mshade.enderman.wrapper.EndermanInventoryKeyWrapper;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;
import io.netty.channel.Channel;

import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class EndermanSessionWrapper extends SessionWrapper {


    private EntityRepository entityRepository;
    private EndermanMaterialWrapper endermanMaterialWrapper;
    private EndermanInventoryKeyWrapper endermanInventoryKeyWrapper;
    private UniqueIdManager uniqueIdManager = new UniqueIdManager();

    public EndermanSessionWrapper(Channel channel, EntityRepository entityRepository, EndermanMaterialWrapper endermanMaterialWrapper, EndermanInventoryKeyWrapper endermanInventoryKeyWrapper) {
        super(channel);
        this.entityRepository = entityRepository;
        this.endermanMaterialWrapper = endermanMaterialWrapper;
        this.endermanInventoryKeyWrapper = endermanInventoryKeyWrapper;
    }

    @Override
    public void sendCompression(int threshold) {
        this.sendPacket(new PacketOutSetCompression(threshold));
        this.enableCompression(threshold);
    }

    @Override
    public void sendLoginSuccess() {
        this.sendPacket(new PacketOutLoginSuccess(this.getGameProfile()));
        this.toggleProtocolStatus(ProtocolStatus.PLAY);
    }

    @Override
    public void sendJoinGame(World world, boolean reducedDebugInfo) {
        MetadataKeyValueBucket metadataKeyValueBucket = world.getMetadataKeyValueBucket();

        Dimension dimension = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIMENSION, Dimension.class);
        Difficulty difficulty = metadataKeyValueBucket.getValueOfMetadataKeyValue(WorldMetadataType.DIFFICULTY, Difficulty.class);

        Player player = ProtocolPipeline.get().getPlayer(this.getChannel());
        sendPacket(new PacketOutJoinGame(player.getEntityId(), player.getGameMode(), dimension, difficulty, 0, world.getName(), reducedDebugInfo));
    }

    @Override
    public void sendHeaderAndFooter(String header, String footer) {
        sendPacket(new PacketOutPlayerList(TextComponent.of(header), TextComponent.of(footer)));
    }

    @Override
    public void sendHeaderAndFooter(TextComponent header, TextComponent footer) {
        sendPacket(new PacketOutPlayerList(header, footer));
    }

    @Override
    public void sendAbilities(boolean invulnerable, boolean flying, boolean allowFlying, boolean instantBreak, float flyingSpeed, float walkSpeed) {
        sendPacket(new PacketOutPlayerAbilities(invulnerable, flying, allowFlying, instantBreak, flyingSpeed, walkSpeed));
    }

    @Override
    public void sendKeepAlive(int threshold) {
        sendPacket(new PacketOutKeepAlive(threshold));
    }

    @Override
    public void sendEncryption(PublicKey publicKey) {
        sendPacket(new PacketOutEncryption(this.getSessionId(), publicKey, this.getVerifyToken()));
    }

    @Override
    public void sendPlayerInfo(PlayerInfoBuilder playerInfoBuilder) {
        sendPacket(new PacketOutPlayerInfo(playerInfoBuilder));
    }

    @Override
    public void sendMessage(TextComponent textComponent, TextPosition textPosition) {
        sendPacket(new PacketOutChatMessage(textComponent, textPosition));
    }

    @Override
    public void sendMessage(TextComponent textComponent) {
        sendMessage(textComponent, TextPosition.CHAT);
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void disconnect(String message) {
        disconnect(TextComponent.of(message));
    }

    @Override
    public void disconnect(TextComponent message) {
        sendPacket(new PacketOutDisconnect(message));
    }

    @Override
    public void teleport(Location location) {
        sendPacket(new PacketOutPlayerPositionAndLook(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
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
        this.sendPacket(new PacketOutEntityTeleport(entity, x, y, z, yaw, pitch, false));
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

        this.sendPacket(new PacketOutEntityRelativeMove(entity, x, y, z));
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

        this.sendPacket(new PacketOutEntityLookRelativeMove(entity, x, y, z, yaw, pitch));
    }

    @Override
    public void sendLook(Entity entity) {
        sendLook(entity, entity.getLocation());
    }

    @Override
    public void sendLook(Entity entity, Location location) {
        this.sendPacket(new PacketOutEntityLook(entity, location));
    }


    @Override
    public void sendHeadLook(Entity entity) {
        sendHeadLook(entity, entity.getLocation());
    }

    @Override
    public void sendHeadLook(Entity entity, Location location) {
        this.sendPacket(new PacketOutEntityHeadLook(entity, location));
    }

    @Override
    public void sendEntity(Entity... entities) {
        for (Entity entity : entities) {
            if(entity instanceof Player) {
                this.sendPacket(new PacketOutSpawnPlayer((Player)entity));
            } else {
                this.sendPacket(new PacketOutSpawnMob(entityRepository.getIdByEntityType(entity.getEntityType()), entity));
            }
            this.sendTeleport(entity);

        }
    }

    @Override
    public void removeEntity(Entity... entities) {
        this.sendPacket(new PacketOutDestroyEntities(entities));
    }

    @Override
    public void sendMetadata(Entity entity, EntityMetadataKey... entityMetadataKeys) {
        sendPacket(new PacketOutEntityMetadata(entity, entityMetadataKeys));
    }

    @Override
    public void sendChunk(Chunk chunk) {
        List<Section> sections = new ArrayList<>();
        for (Section sectionBuffer : chunk.getSections()) {
            if (sectionBuffer != null && sectionBuffer.getRealBlock() != 0) {
                sections.add(sectionBuffer);
            }
        }
        int capacity = sections.size() * (4096 * 4) + 256;

        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        for (Section sectionBuffer : sections) {
            for (int i = 0; i < 4096; i++) {
                MaterialKey materialKey = sectionBuffer.getBlock(i);
                MaterialKey wrap = endermanMaterialWrapper.wrap(materialKey);
                byteBuffer.put((byte) (wrap.getId() << 4 | wrap.getMetadata()));
                byteBuffer.put((byte) (wrap.getId() >> 4));
            }
        }

        for (Section sectionBuffer : sections) {
            byteBuffer.put(sectionBuffer.getBlockLight().getRawData());
        }

        for (Section sectionBuffer : sections) {
            byteBuffer.put(sectionBuffer.getSkyLight().getRawData());
        }

        byteBuffer.put(chunk.getBiomes());

        sendPacket(new PacketOutChunkData(chunk.getX(), chunk.getZ(), true, chunk.getBitMask(), byteBuffer.array()));
    }

    @Override
    public void sendSection(Section section) {

    }

    @Override
    public void sendUnloadChunk(Chunk chunk) {
        sendPacket(new PacketOutChunkData(chunk.getX(), chunk.getZ(), true, 0, new byte[0]));
    }

    @Override
    public void sendBlockChange(Vector blockPosition, MaterialKey materialKey) {
        sendPacket(new PacketOutBlockChange(blockPosition, endermanMaterialWrapper.wrap(materialKey)));
    }

    @Override
    public void sendOpenInventory(Inventory inventory) {
        if (inventory instanceof PlayerInventory) return;
        ProtocolPipeline.get().getPlayer(channel).setOpenedInventory(inventory);
        /*
        int freeId = this.uniqueIdManager.getFreeId();
        this.inventoryRepository.register(freeId, inventory);

         */
        sendPacket(new PacketOutOpenInventory(endermanInventoryKeyWrapper, 1, inventory));
    }

    @Override
    public void sendCloseInventory(Inventory inventory) {
        int id = this.inventoryRepository.getIdOfInventory(inventory);
        sendPacket(new PacketOutCloseInventory(id));
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



        sendPacket(new PacketOutInventoryItems(id, inventory));
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
        sendPacket(new PacketOutSetItemStack(slot, id, itemStack));
    }

    @Override
    public void sendDisplayScoreboard(Scoreboard<?> scoreboard) {
        sendPacket(new PacketOutDisplayScoreboard(scoreboard));
    }

    @Override
    public void sendScoreboardObjective(Scoreboard<?> scoreboard, ScoreboardMode scoreboardMode) {
        sendPacket(new PacketOutScoreboardObjective(scoreboard, scoreboardMode));
    }

    @Override
    public void sendUpdateScoreboard(ScoreboardObjective<?> scoreboardObjective, ScoreboardObjectiveAction scoreboardObjectiveAction) {
        sendPacket(new PacketOutUpdateScoreboard(scoreboardObjective, scoreboardObjectiveAction));
    }

    @Override
    public void sendSoundEffect(SoundEffect soundEffect) {
        sendPacket(new PacketOutSoundEffect(soundEffect));
    }

    @Override
    public void sendTitle(TitleAction titleAction, Title title) {
        sendPacket(new PacketOutTitle(titleAction, title));
    }

    private boolean hasOverflow(int value) {
        return value > 3 || value < -3;
    }

    private int floor(double d0) {
        int i = (int) d0;

        return d0 < (double) i ? i - 1 : i;
    }
}
