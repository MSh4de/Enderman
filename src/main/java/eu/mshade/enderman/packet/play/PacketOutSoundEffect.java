package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.sound.SoundEffect;

public class PacketOutSoundEffect implements PacketOut {

    private final SoundEffect soundEffect;

    public PacketOutSoundEffect(SoundEffect soundEffect) {
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
