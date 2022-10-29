package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.particle.*;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.wrapper.Wrapper;

public class PacketOutParticle implements PacketOut {

    private final Wrapper<MaterialKey, MaterialKey> materialKeyWrapper;
    private final Wrapper<ParticleKey, Integer> particleKeyWrapper;
    private final Particle particle;

    public PacketOutParticle(Wrapper<MaterialKey, MaterialKey> materialKeyWrapper, Wrapper<ParticleKey, Integer> particleKeyWrapper, Particle particle) {
        this.materialKeyWrapper = materialKeyWrapper;
        this.particleKeyWrapper = particleKeyWrapper;
        this.particle = particle;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        Integer particleKey = particleKeyWrapper.wrap(particle.getParticleKey());

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
            MaterialKey materialKey = materialKeyWrapper.wrap(particleIconCrack.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId());
            protocolBuffer.writeVarInt(particleIconCrack.getMetadata());
        } else if (particle instanceof ParticleBlockCrack particleBlockCrack) {
            MaterialKey materialKey = materialKeyWrapper.wrap(particleBlockCrack.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId() + (particleBlockCrack.getMetadata() << 12));
        } else if (particle instanceof ParticleBlockDust particleBlockDust) {
            MaterialKey materialKey = materialKeyWrapper.wrap(particleBlockDust.getMaterial());

            if (materialKey == null) return;

            protocolBuffer.writeVarInt(materialKey.getId());
        }
    }
}
