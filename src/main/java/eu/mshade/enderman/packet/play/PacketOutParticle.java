package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.particle.*;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;
import eu.mshade.enderman.wrapper.EndermanParticleWrapper;

public class PacketOutParticle implements PacketOut {

    private final EndermanMaterialWrapper endermanMaterialWrapper;
    private final EndermanParticleWrapper endermanParticleWrapper;
    private final Particle particle;

    public PacketOutParticle(EndermanMaterialWrapper endermanMaterialWrapper, EndermanParticleWrapper endermanParticleWrapper, Particle particle) {
        this.endermanMaterialWrapper = endermanMaterialWrapper;
        this.endermanParticleWrapper = endermanParticleWrapper;
        this.particle = particle;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        Integer particleKey = endermanParticleWrapper.wrap(particle.getParticleKey());

        if (particleKey == null) return;

        protocolBuffer.writeInt(particleKey);
        protocolBuffer.writeBoolean(particle.isLongDistance());
        protocolBuffer.writeFloat((float) particle.getParticleVector().getX());
        protocolBuffer.writeFloat((float) particle.getParticleVector().getY());
        protocolBuffer.writeFloat((float) particle.getParticleVector().getZ());
        protocolBuffer.writeFloat((float) particle.getParticleOffsetVector().getX());
        protocolBuffer.writeFloat((float) particle.getParticleOffsetVector().getY());
        protocolBuffer.writeFloat((float) particle.getParticleOffsetVector().getZ());
        protocolBuffer.writeFloat(particle.getParticleSpeed());
        protocolBuffer.writeInt(particle.getParticleCount());

        if (particle instanceof ParticleIconCrack particleIconCrack) {
            MaterialKey materialKey = endermanMaterialWrapper.wrap(particleIconCrack.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId());
            protocolBuffer.writeVarInt(particleIconCrack.getMetadata());
        } else if (particle instanceof ParticleBlockCrack particleBlockCrack) {
            MaterialKey materialKey = endermanMaterialWrapper.wrap(particleBlockCrack.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId() + (particleBlockCrack.getMetadata() << 12));
        } else if (particle instanceof ParticleBlockDust particleBlockDust) {
            MaterialKey materialKey = endermanMaterialWrapper.wrap(particleBlockDust.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId());
        }
    }
}
