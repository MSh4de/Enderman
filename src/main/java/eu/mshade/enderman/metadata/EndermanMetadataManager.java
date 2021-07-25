package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.metadata.MetadataEntry;
import eu.mshade.enderframe.metadata.MetadataManager;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.MetadataTypeRepository;
import eu.mshade.enderframe.metadata.buffer.MetadataBuffer;
import eu.mshade.enderframe.metadata.buffer.type.*;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderman.metadata.entity.*;

public class EndermanMetadataManager extends MetadataManager {

    public EndermanMetadataManager() {
        MetadataTypeRepository metadataTypeRepository = getMetadataTypeRepository();
        
        metadataTypeRepository.registerMetadataIndex(MetadataType.BYTE,0);
        metadataTypeRepository.registerMetadataIndex(MetadataType.SHORT,1);
        metadataTypeRepository.registerMetadataIndex(MetadataType.INTEGER,2);
        metadataTypeRepository.registerMetadataIndex(MetadataType.FLOAT,3);
        metadataTypeRepository.registerMetadataIndex(MetadataType.STRING,4);
        metadataTypeRepository.registerMetadataIndex(MetadataType.SLOT,5);
        metadataTypeRepository.registerMetadataIndex(MetadataType.BLOCK_POSITION,6);
        metadataTypeRepository.registerMetadataIndex(MetadataType.ROTATION,7);
        
        registerEntityBuffer(EntityType.PLAYER, new PlayerMetadataBuffer());
        registerEntityBuffer(EntityType.ZOMBIE, new ZombieMetadataBuffer());
        registerEntityBuffer(EntityType.END_CRYSTAL, new EnderCrystalMetadataBuffer());
        registerEntityBuffer(EntityType.BOAT, new BoatMetadataBuffer());
        registerEntityBuffer(EntityType.SPIDER, new SpiderMetadataBuffer());

        registerMetadataTypeBuffer(MetadataType.BYTE, new ByteMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.SHORT, new ShortMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.INTEGER, new IntegerMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.FLOAT, new FloatMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.STRING, new StringMetadataTypeBuffer());
        //registerMetadataTypeBuffer(MetadataType.SLOT, new ());
        registerMetadataTypeBuffer(MetadataType.BLOCK_POSITION, new BlockPositionMetadataTypeBuffer());
        registerMetadataTypeBuffer(MetadataType.ROTATION, new RotationMetadataTypeBuffer());
    }

    @Override
    public void write(ByteMessage byteMessage, EntityType entityType, MetadataEntry metadataEntry) {
        MetadataBuffer entityBuffer = this.getEntityBuffer(entityType);

        int i = (this.getMetadataTypeRepository().getMetadataIndex(metadataEntry.getMetadata().getMetadataType()) << 5 | entityBuffer.getMetadataRepository().getMetadataIndex(metadataEntry.getMetadataMeaning()));
        byteMessage.writeByte(i);
        entityBuffer.write(byteMessage, this, metadataEntry);
    }
}
