package eu.mshade.enderman.metadata.entity.type

import eu.mshade.enderframe.entity.Guardian
import eu.mshade.enderframe.entity.metadata.EntityMetadataKey
import eu.mshade.enderframe.metadata.Metadata
import eu.mshade.enderframe.metadata.MetadataWrapper
import eu.mshade.enderframe.metadata.type.IntegerMetadata

class GuardianPropertiesMetadataWrapper: MetadataWrapper<Guardian> {

    override fun wrap(guardian: Guardian): Metadata<*> {
        val metadataKeyValueBucket = guardian.metadataKeyValueBucket

        val elderly = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.IS_ELDERLY)?.metadataValue)?: false) as Boolean
        val retractingSpikes = ((metadataKeyValueBucket.getMetadataKeyValue(EntityMetadataKey.IS_RETRACTING_SPIKES)?.metadataValue)?: false) as Boolean

        var b = 0
        if (elderly)
            b = b or 0x02

        if (retractingSpikes)
            b = b or 0x04

        return IntegerMetadata(b)
    }
}