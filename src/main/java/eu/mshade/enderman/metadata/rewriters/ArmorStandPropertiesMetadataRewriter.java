package eu.mshade.enderman.metadata.rewriters;

import eu.mshade.enderframe.entity.ArmorStand;
import eu.mshade.enderframe.entity.Entity;
import eu.mshade.enderframe.metadata.MetadataType;
import eu.mshade.enderframe.metadata.type.ByteMetadata;
import eu.mshade.enderframe.metadata.v2.MetadataManager;
import eu.mshade.enderframe.metadata.v2.MetadataRewriter;
import eu.mshade.enderframe.protocol.ByteMessage;

public class ArmorStandPropertiesMetadataRewriter extends MetadataRewriter {

    @Override
    public void write(MetadataManager metadataManager, ByteMessage byteMessage, Entity entity) {
        byte b = 0;
        ArmorStand armorStand = (ArmorStand) entity;
        if (armorStand.isSmall()) b = (byte) (b | 0x01);

        if (armorStand.hasGravity()) b = (byte) (b | 0x02);

        if (armorStand.hasArms()) b = (byte) (b | 0x04);

        if (armorStand.removeBasePlate()) b = (byte) (b | 0x08);

        if (armorStand.marker()) b = (byte) (b | 0x10);

        metadataManager.getMetadataTypeBuffer(MetadataType.BYTE).write(byteMessage,new ByteMetadata(b));
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.BYTE;
    }
}
