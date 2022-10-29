package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.packetevent.PacketEncryptionEvent;
import eu.mshade.enderman.packet.login.PacketInEncryption;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketEncryptionListener implements EventListener<PacketInEncryption> {

    @Override
    public void onEvent(PacketInEncryption event) {
        EnderFrame.get().getPacketEventBus().publish(new PacketEncryptionEvent(event.getSessionWrapper(), event.getSharedSecret(), event.getVerifyToken()));
    }
}
