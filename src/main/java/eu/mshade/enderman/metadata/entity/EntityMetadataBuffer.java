package eu.mshade.enderman.metadata.entity;

import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.metadata.MetadataMeaning;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.buffer.MetadataBuffer;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.protocol.ByteMessage;

public class EntityMetadataBuffer extends MetadataBuffer {

    public EntityMetadataBuffer() {
        this.getMetadataRepository().registerMetadataIndex(0, MetadataType.BYTE, MetadataMeaning.ENTITY_PROPERTIES);
        this.getMetadataRepository().registerMetadataIndex(1, MetadataType.SHORT, MetadataMeaning.AIR_TICKS);
        this.getMetadataRepository().registerMetadataIndex(2, MetadataType.STRING, MetadataMeaning.CUSTOM_NAME);
        this.getMetadataRepository().registerMetadataIndex(3, MetadataType.BYTE, MetadataMeaning.IS_CUSTOM_NAME_VISIBLE);
        this.getMetadataRepository().registerMetadataIndex(4, MetadataType.BYTE, MetadataMeaning.IS_SILENT);
    }

    @Override
    public void write(ByteMessage byteMessage, MetadataManager metadataManager, MetadataEntry metadataEntry) {
        metadataManager.getMetadataTypeBuffer(metadataEntry.getMetadata().getMetadataType()).write(byteMessage, metadataEntry.getMetadata());
    }
}
