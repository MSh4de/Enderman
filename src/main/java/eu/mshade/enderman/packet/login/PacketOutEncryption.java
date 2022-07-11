package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

import java.security.PublicKey;

public class PacketOutEncryption implements PacketOut {

    private String hashedServerId;
    private PublicKey publicKey;
    private byte[] verifyToken;

    public PacketOutEncryption(String hashedServerId, PublicKey publicKey, byte[] verifyToken) {
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
