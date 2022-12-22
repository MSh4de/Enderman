package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInPlayerAbilities implements MinecraftPacketIn {

    private boolean invulnerable;
    private boolean flying;
    private boolean allowFlying;
    private boolean instantBreak;
    private float flyingSpeed;
    private float walkSpeed;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        byte b = minecraftByteBuf.readByte();

        this.invulnerable = b == (b | 0x08);
        this.allowFlying = b == (b | 0x04);
        this.flying = b == (b | 0x02);
        this.instantBreak = b == (b | 0x01);


        this.flyingSpeed = minecraftByteBuf.readFloat();
        this.walkSpeed = minecraftByteBuf.readFloat();
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
    public MinecraftSession getMinecraftSession() {
        return minecraftSession;
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
