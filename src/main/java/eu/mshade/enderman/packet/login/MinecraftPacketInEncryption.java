package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInEncryption implements MinecraftPacketIn {

    private MinecraftSession minecraftSession;
    private byte[] sharedSecret;
    private byte[] verifyToken;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        this.sharedSecret = minecraftByteBuf.readByteArray();
        this.verifyToken = minecraftByteBuf.readByteArray();
    }

    @Override
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }
}
