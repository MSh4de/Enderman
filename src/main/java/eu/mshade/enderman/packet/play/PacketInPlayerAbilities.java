package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInPlayerAbilities implements PacketIn {

    private boolean invulnerable;
    private boolean flying;
    private boolean allowFlying;
    private boolean instantBreak;
    private float flyingSpeed;
    private float walkSpeed;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
        byte b = protocolBuffer.readByte();

        this.invulnerable = b == (b | 0x08);
        this.allowFlying = b == (b | 0x04);
        this.flying = b == (b | 0x02);
        this.instantBreak = b == (b | 0x01);


        this.flyingSpeed = protocolBuffer.readFloat();
        this.walkSpeed = protocolBuffer.readFloat();
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isAllowFlying() {
        return allowFlying;
    }

    public boolean isInstantBreak() {
        return instantBreak;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    @Override
    public String toString() {
        return "PacketInPlayerAbilities{" +
                "invulnerable=" + invulnerable +
                ", flying=" + flying +
                ", allowFlying=" + allowFlying +
                ", instantBreak=" + instantBreak +
                ", flyingSpeed=" + flyingSpeed +
                ", walkSpeed=" + walkSpeed +
                '}';
    }
}
