package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class MinecraftPacketInEncryption implements MinecraftPacketIn {

    private SessionWrapper sessionWrapper;
    private byte[] sharedSecret;
    private byte[] verifyToken;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        this.sharedSecret = protocolBuffer.readByteArray();
        this.verifyToken = protocolBuffer.readByteArray();
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
