package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.mojang.GameProfile;
import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketOutLoginSuccess implements PacketOut {

    private GameProfile gameProfile;

    public PacketOutLoginSuccess(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(gameProfile.getId().toString());
        protocolBuffer.writeString(gameProfile.getName());
    }
}
