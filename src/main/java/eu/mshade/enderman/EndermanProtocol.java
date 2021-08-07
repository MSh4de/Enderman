package eu.mshade.enderman;

import eu.mshade.enderframe.EnderFrameProtocol;
import eu.mshade.enderframe.EnderFrameSession;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.entity.EntityType;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.ProtocolStatus;
import eu.mshade.enderframe.protocol.ProtocolVersion;
import eu.mshade.enderframe.protocol.packet.*;
import eu.mshade.enderman.listener.*;
import eu.mshade.enderman.packet.login.PacketInEncryption;
import eu.mshade.enderman.packet.login.PacketInLogin;
import eu.mshade.enderman.packet.login.PacketOutEncryption;
import eu.mshade.enderman.packet.login.PacketOutLoginSuccess;
import eu.mshade.enderman.packet.play.*;
import io.netty.buffer.ByteBuf;

public class EndermanProtocol extends EnderFrameProtocol {


    public EndermanProtocol() {
        this.getEventBus().subscribe(PacketInKeepAlive.class, new PacketKeepAliveListener());
        this.getEventBus().subscribe(PacketInLogin.class, new PacketLoginListener());
        this.getEventBus().subscribe(PacketInEncryption.class, new PacketEncryptionListener());
        this.getEventBus().subscribe(PacketInClientSettings.class, new PacketClientSettingsListener());
        this.getEventBus().subscribe(PacketInPlayerPosition.class, new PacketPlayerPositionListener());
        this.getEventBus().subscribe(PacketInPlayerGround.class, new PacketPlayerGroundListener());
        this.getEventBus().subscribe(PacketInPlayerLook.class, new PacketPlayerLookListener());
        this.getEventBus().subscribe(PacketInPlayerPositionAndLook.class, new PacketPlayerPositionAndLookListener());
        this.getEventBus().subscribe(PacketInChatMessage.class, new PacketChatMessageListener());

        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x00, PacketOutDisconnect.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x01, PacketOutEncryption.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x02, PacketOutLoginSuccess.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.LOGIN, 0x03, PacketOutSetCompression.class);

        this.getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x00, PacketInLogin.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.LOGIN, 0x01, PacketInEncryption.class);

        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x00, PacketInKeepAlive.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x01, PacketInChatMessage.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x03, PacketInPlayerGround.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x04, PacketInPlayerPosition.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x05, PacketInPlayerLook.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x06, PacketInPlayerPositionAndLook.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x08, PacketInPlayerBlockPlacement.class);
        this.getProtocolRegistry().registerIn(ProtocolStatus.PLAY, 0x15, PacketInClientSettings.class);

        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x00, PacketOutKeepAlive.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x01, PacketOutJoinGame.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x02, PacketOutChatMessage.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x05, PacketOutSpawnPosition.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x07, PacketOutRespawn.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x08, PacketOutPlayerPositionAndLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x0C, PacketOutSpawnPlayer.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x0F,  PacketOutSpawnMob.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x13,  PacketOutDestroyEntities.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x14,  PacketOutSpawnEntity.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x15,  PacketOutEntityRelativeMove.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x16,  PacketOutEntityLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x17,  PacketOutEntityLookRelativeMove.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x18,  PacketOutEntityTeleport.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x19, PacketOutEntityHeadLook.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x1C,  PacketOutEntityMetadata.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY,0x20,  PacketOutEntityProperties.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x21, PacketOutChunkData.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x38, PacketOutPlayerInfo.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x39, PacketOutPlayerAbilities.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x40, PacketOutDisconnect.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x46, PacketOutSetCompression.class);
        this.getProtocolRegistry().registerOut(ProtocolStatus.PLAY, 0x47, PacketOutPlayerList.class);

        this.getEntityRepository().registerEntityTypeId(54, EntityType.ZOMBIE);
    }

    @Override
    public ByteMessage getByteMessage(ByteBuf byteBuf) {
        return new EndermanByteMessage(byteBuf);
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.V1_8;
    }

    @Override
    public EnderFrameSession getEnderFrameSession(EnderFrameSessionHandler enderFrameSessionHandler) {
        return new EndermanSession(enderFrameSessionHandler);
    }
}
