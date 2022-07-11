package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInEncryption implements PacketIn {

    private byte[] sharedSecret;
    private byte[] verifyToken;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        this.sharedSecret = protocolBuffer.readByteArray();
        this.verifyToken = protocolBuffer.readByteArray();
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
