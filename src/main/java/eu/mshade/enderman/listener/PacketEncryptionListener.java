package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.MinecraftPacketEncryptionEvent;
import eu.mshade.enderman.packet.login.MinecraftPacketInEncryption;
import eu.mshade.mwork.event.EventListener;

public class PacketEncryptionListener implements EventListener<MinecraftPacketInEncryption> {

    @Override
    public void onEvent(MinecraftPacketInEncryption event) {
        EnderFrame.get().getPacketEventBus().publish(new MinecraftPacketEncryptionEvent(event.getSessionWrapper(), event.getSharedSecret(), event.getVerifyToken()));
    }
}
