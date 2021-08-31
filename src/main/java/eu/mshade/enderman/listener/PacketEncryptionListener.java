package eu.mshade.enderman.listener;

import eu.mshade.enderframe.EnderFrame;
import eu.mshade.enderframe.EnderFrameSessionHandler;
import eu.mshade.enderframe.packetevent.PacketEncryptionEvent;
import eu.mshade.enderman.packet.login.PacketInEncryption;
import eu.mshade.mwork.ParameterContainer;
import eu.mshade.mwork.event.EventListener;

public class PacketEncryptionListener implements EventListener<PacketInEncryption> {

    @Override
    public void onEvent(PacketInEncryption event, ParameterContainer eventContainer) {
        EnderFrame.get().getPacketEventBus().publish(new PacketEncryptionEvent(eventContainer.getContainer(EnderFrameSessionHandler.class).getEnderFrameSession().getPlayer(),
                event.getSharedSecret(), event.getVerifyToken()), eventContainer);
    }
}
