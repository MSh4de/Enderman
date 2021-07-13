package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.mojang.GameProfile;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

public class PacketOutLoginSuccess extends PacketOut {

    private GameProfile gameProfile;

    public PacketOutLoginSuccess(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeString(gameProfile.getId().toString());
        byteMessage.writeString(gameProfile.getName());
    }
}
