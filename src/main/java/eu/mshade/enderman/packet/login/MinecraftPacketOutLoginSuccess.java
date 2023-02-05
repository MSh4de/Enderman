package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.mojang.GameProfile;
import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

public class MinecraftPacketOutLoginSuccess implements MinecraftPacketOut {

    private GameProfile gameProfile;

    public MinecraftPacketOutLoginSuccess(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    @Override
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(gameProfile.getId().toString());
        minecraftByteBuf.writeString(gameProfile.getName());
    }
}
