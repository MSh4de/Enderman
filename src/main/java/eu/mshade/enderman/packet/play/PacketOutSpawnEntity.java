package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.type.*;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderman.metadata.EndermanMetadataManager;

import java.util.ArrayList;
import java.util.List;

public class PacketOutSpawnEntity extends PacketOut {
    private final int aInt;

    public PacketOutSpawnEntity(int aInt) {
        this.aInt = aInt;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeVarInt(aInt);
    }

}
