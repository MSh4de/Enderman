package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.sound.SoundEffect;

public class MinecraftPacketOutSoundEffect implements MinecraftPacketOut {

    private final SoundEffect soundEffect;

    public MinecraftPacketOutSoundEffect(SoundEffect soundEffect) {
        this.soundEffect = soundEffect;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(soundEffect.getSoundName());
        protocolBuffer.writeInt(soundEffect.getSoundPosition().getX());
        protocolBuffer.writeInt(soundEffect.getSoundPosition().getY());
        protocolBuffer.writeInt(soundEffect.getSoundPosition().getZ());
        protocolBuffer.writeFloat(soundEffect.getSoundVolume());
        protocolBuffer.writeByte(soundEffect.getSoundPitch());
    }
}
