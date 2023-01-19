package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.particle.*;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutParticle implements MinecraftPacketOut {

    private final Wrapper<MaterialKey, MaterialKey> materialKeyWrapper;
    private final Wrapper<ParticleKey, Integer> particleKeyWrapper;
    private final Particle particle;

    public MinecraftPacketOutParticle(Wrapper<MaterialKey, MaterialKey> materialKeyWrapper, Wrapper<ParticleKey, Integer> particleKeyWrapper, Particle particle) {
        this.materialKeyWrapper = materialKeyWrapper;
        this.particleKeyWrapper = particleKeyWrapper;
        this.particle = particle;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        Integer particleKey = particleKeyWrapper.wrap(particle.getParticleKey());

        if (particleKey == null) return;

        minecraftByteBuf.writeInt(particleKey);
        minecraftByteBuf.writeBoolean(particle.isLongDistance());
        minecraftByteBuf.writeFloat((float) particle.getParticleVector().getX());
        minecraftByteBuf.writeFloat((float) particle.getParticleVector().getY());
        minecraftByteBuf.writeFloat((float) particle.getParticleVector().getZ());
        minecraftByteBuf.writeFloat((float) particle.getParticleOffsetVector().getX());
        minecraftByteBuf.writeFloat((float) particle.getParticleOffsetVector().getY());
        minecraftByteBuf.writeFloat((float) particle.getParticleOffsetVector().getZ());
        minecraftByteBuf.writeFloat(particle.getParticleSpeed());
        minecraftByteBuf.writeInt(particle.getParticleCount());

        if (particle instanceof ParticleIconCrack particleIconCrack) {
            MaterialKey materialKey = materialKeyWrapper.wrap(particleIconCrack.getMaterial());

            if (materialKey == null) return;

            minecraftByteBuf.writeVarInt(materialKey.getId());
            minecraftByteBuf.writeVarInt(particleIconCrack.getMetadata());
        } else if (particle instanceof ParticleBlockCrack particleBlockCrack) {
            MaterialKey materialKey = materialKeyWrapper.wrap(particleBlockCrack.getMaterial());

            if (materialKey == null) return;

            minecraftByteBuf.writeVarInt(materialKey.getId() + (particleBlockCrack.getMetadata() << 12));
        } else if (particle instanceof ParticleBlockDust particleBlockDust) {
            MaterialKey materialKey = materialKeyWrapper.wrap(particleBlockDust.getMaterial());

            if (materialKey == null) return;

            minecraftByteBuf.writeVarInt(materialKey.getId());
        }
    }
}
