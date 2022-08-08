package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.particle.Particle;
import eu.mshade.enderframe.particle.ParticleDust;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

public class PacketOutParticle implements PacketOut {

    private final int particleKey;
    private final Particle particle;

    public PacketOutParticle(int particleKey, Particle particle) {
        this.particleKey = particleKey;
        this.particle = particle;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
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

        if (particle instanceof ParticleDust particleDust) {
            ByteBuf particleBuffer = Unpooled.buffer();
            particleBuffer.writeFloat(particleDust.getRed());
            particleBuffer.writeFloat(particleDust.getGreen());
            particleBuffer.writeFloat(particleDust.getBlue());
            particleBuffer.writeFloat(particleDust.getScale());

            protocolBuffer.writeBytes(particleBuffer);
        }
    }
}
