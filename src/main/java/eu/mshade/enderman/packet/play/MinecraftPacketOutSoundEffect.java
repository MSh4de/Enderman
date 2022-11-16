package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.sound.SoundEffect;

public class MinecraftPacketOutSoundEffect implements MinecraftPacketOut {

    private final SoundEffect soundEffect;

    public MinecraftPacketOutSoundEffect(SoundEffect soundEffect) {
        this.soundEffect = soundEffect;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(soundEffect.getSoundName());
        minecraftByteBuf.writeInt(soundEffect.getSoundPosition().getX());
        minecraftByteBuf.writeInt(soundEffect.getSoundPosition().getY());
        minecraftByteBuf.writeInt(soundEffect.getSoundPosition().getZ());
        minecraftByteBuf.writeFloat(soundEffect.getSoundVolume());
        minecraftByteBuf.writeByte(soundEffect.getSoundPitch());
    }
}
