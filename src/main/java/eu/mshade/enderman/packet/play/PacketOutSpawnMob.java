package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.type.*;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderman.metadata.EndermanMetadataManager;

import java.util.ArrayList;
import java.util.List;

public class PacketOutSpawnMob extends PacketOut {

    private MetadataManager metadataManager = new EndermanMetadataManager();

    private final int id;
    private final int entityId;
    //private final EntityType entityType;
    private final int x,y,z;


    public PacketOutSpawnMob(int id, int entityId, int x, int y, int z) {
        this.id = id;
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(id);
        byteMessage.writeByte(entityId);
        byteMessage.writeInt(x*32);
        byteMessage.writeInt(y*32);
        byteMessage.writeInt(z*32);
        byteMessage.writeByte(1);
        byteMessage.writeByte(1);
        byteMessage.writeByte(1);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        byteMessage.writeShort(0);
        //get().forEach(metadataEntry -> metadataManager.write(byteMessage, EntityType.ZOMBIE,metadataEntry));
        byteMessage.writeByte(0x7F);
    }

    public List<MetadataEntry> get(){
        List<MetadataEntry> metadataEntries = new ArrayList<>();
        metadataEntries.add(new MetadataEntry(MetadataMeaning.ENTITY_PROPERTIES, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.AIR_TICKS, new ShortMetadata((short) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.CUSTOM_NAME, new StringMetadata("")));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CUSTOM_NAME_VISIBLE, new ByteMetadata((byte) 1)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_SILENT, new ByteMetadata((byte) 0)));

        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CHILD, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_VILLAGER, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CONVERTING, new ByteMetadata((byte) 0)));

        /*metadataEntries.add(new MetadataEntry(MetadataMeaning.HEALTH, new FloatMetadata((float) 1)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.POTION_EFFECT_COLOR, new IntegerMetadata( 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_POTION_EFFECT_AMBIENT, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.NUMBER_OF_ARROWS_IN_ENTITY, new ByteMetadata((byte) 0)));

        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CHILD, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_VILLAGER, new ByteMetadata((byte) 0)));
        metadataEntries.add(new MetadataEntry(MetadataMeaning.IS_CONVERTING, new ByteMetadata((byte) 0)));*/


        return metadataEntries;
    }

}
