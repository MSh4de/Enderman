package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.*;
import eu.mshade.enderframe.metadata.type.*;
import eu.mshade.enderframe.mojang.chat.TextComponent;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.world.BlockPosition;
import eu.mshade.enderframe.world.Position;
import eu.mshade.enderman.metadata.EndermanMetadataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacketOutSpawnPlayer extends PacketOut {

    private int id;
    private UUID uuid;
    private Position position;
    private MetadataManager metadataManager = new EndermanMetadataManager();

    public PacketOutSpawnPlayer(int id, UUID uuid, Position position) {
        this.id = id;
        this.uuid = uuid;
        this.position = position;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        try {
            byteMessage.writeVarInt(id);
            byteMessage.writeUUID(uuid);
            byteMessage.writeInt((int) position.getX() *32);
            byteMessage.writeInt((int) position.getY() *32);
            byteMessage.writeInt((int) position.getZ() *32);
            byteMessage.writeByte((int) position.getYaw());
            byteMessage.writeByte((int) position.getPitch());
            byteMessage.writeShort(0);
            get().forEach(metadataEntry -> metadataManager.write(byteMessage, EntityType.PLAYER, metadataEntry));
            byteMessage.writeByte(0x7F);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<MetadataEntry> get(){
        List<MetadataEntry> metadataEntries = new ArrayList<>();
        metadataEntries.add(new MetadataEntry(MetadataMeaning.ENTITY_PROPERTIES, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.AIR_TICKS, new ShortMetadata((short) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.CUSTOM_NAME, new StringMetadata("")));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CUSTOM_NAME_VISIBLE, new ByteMetadata((byte) 1)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_SILENT, new ByteMetadata((byte) 0)));

        /*metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CHILD, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_VILLAGER, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CONVERTING, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.ENTITY_PROPERTIES, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.AIR_TICKS, new ShortMetadata((short) 300)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CUSTOM_NAME_VISIBLE, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.CUSTOM_NAME, new StringMetadata("")));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_SILENT, new ByteMetadata((byte) 0)));*/

        metadataEntries.add(new MetadataEntry(MetadataMeaning.HEALTH, new FloatMetadata((float) 1)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.POTION_EFFECT_COLOR, new IntegerMetadata( 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_POTION_EFFECT_AMBIENT, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.NUMBER_OF_ARROWS_IN_ENTITY, new ByteMetadata((byte) 0)));

        metadataEntries.add(new MetadataEntry(MetadataMeaning.UNUSED, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.ABSORPTION_HEARTS, new FloatMetadata((float) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.SCORE, new IntegerMetadata( 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.SKIN_PART, new ByteMetadata((byte) 0xFF)));
        return metadataEntries;
    }
}
