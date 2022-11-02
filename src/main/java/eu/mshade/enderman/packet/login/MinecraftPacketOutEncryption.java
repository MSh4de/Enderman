package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

import java.security.PublicKey;

public class MinecraftPacketOutEncryption implements MinecraftPacketOut {

    private String hashedServerId;
    private PublicKey publicKey;
    private byte[] verifyToken;

    public MinecraftPacketOutEncryption(String hashedServerId, PublicKey publicKey, byte[] verifyToken) {
        this.hashedServerId = hashedServerId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeString(this.hashedServerId);
        protocolBuffer.writeByteArray(this.publicKey.getEncoded());
        protocolBuffer.writeByteArray(this.verifyToken);
    }
}
