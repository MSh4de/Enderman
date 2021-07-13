package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketOut;

import java.security.PublicKey;

public class PacketOutEncryption extends PacketOut {

    private String hashedServerId;
    private PublicKey publicKey;
    private byte[] verifyToken;

    public PacketOutEncryption(String hashedServerId, PublicKey publicKey, byte[] verifyToken) {
        this.hashedServerId = hashedServerId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public void serialize(ByteMessage byteMessage) {
        byteMessage.writeString(this.hashedServerId);
        byteMessage.writeByteArray(this.publicKey.getEncoded());
        byteMessage.writeByteArray(this.verifyToken);

    }
}
