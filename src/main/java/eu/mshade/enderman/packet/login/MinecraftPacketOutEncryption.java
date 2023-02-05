package eu.mshade.enderman.packet.login;

import eu.mshade.enderframe.protocol.MinecraftPacketOut;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;

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
    public void serialize(MinecraftByteBuf minecraftByteBuf) {
        minecraftByteBuf.writeString(this.hashedServerId);
        minecraftByteBuf.writeByteArray(this.publicKey.getEncoded());
        minecraftByteBuf.writeByteArray(this.verifyToken);
    }
}
