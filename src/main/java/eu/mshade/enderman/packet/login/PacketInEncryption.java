package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInEncryption extends PacketIn {

    private byte[] sharedSecret;
    private byte[] verifyToken;

    @Override
    public void deserialize(ByteMessage byteMessage) {
        this.sharedSecret = byteMessage.readByteArray();
        this.verifyToken = byteMessage.readByteArray();
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
