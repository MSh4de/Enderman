package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.sound.Sound;

public class PacketOutSoundEffect implements PacketOut {

    private final Sound sound;

    public PacketOutSoundEffect(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(sound.getSoundName().getName());
        protocolBuffer.writeInt(sound.getSoundPosition().getX());
        protocolBuffer.writeInt(sound.getSoundPosition().getY());
        protocolBuffer.writeInt(sound.getSoundPosition().getZ());
        protocolBuffer.writeFloat(sound.getSoundVolume());
        protocolBuffer.writeByte(63);
    }
}
