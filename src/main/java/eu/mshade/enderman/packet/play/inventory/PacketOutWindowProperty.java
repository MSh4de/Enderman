package eu.mshade.enderman.packet.play.inventory;

import eu.mshade.enderframe.protocol.PacketOut;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.world.block.window.IWindowProperty;

public class PacketOutWindowProperty implements PacketOut {

    private final int windowId;

    private final int property;
    private final int value;

    public PacketOutWindowProperty(int windowId, IWindowProperty property, int value) {
        this(windowId, property.getId(), value);
    }

    public PacketOutWindowProperty(int windowId, int property, int value) {
        this.windowId = windowId;
        this.property = property;
        this.value = value;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public int getProperty() {
        return this.property;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void serialize(ProtocolBuffer protocolBuffer) {
        protocolBuffer.writeByte(this.windowId);
        protocolBuffer.writeShort(this.property);
        protocolBuffer.writeShort(this.value);
    }
}
