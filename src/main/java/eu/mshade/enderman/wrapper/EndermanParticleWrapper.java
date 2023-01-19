package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.particle.ParticleType;
import eu.mshade.enderframe.particle.ParticleKey;

public class EndermanParticleWrapper extends Wrapper<ParticleKey, Integer> {

    public EndermanParticleWrapper() {
        this.registerInput(ParticleType.POOF, 0);
        this.registerInput(ParticleType.EXPLOSION, 1);
        this.registerInput(ParticleType.EXPLOSION_EMITTER, 2);
        this.registerInput(ParticleType.FIREWORK, 3);
        this.registerInput(ParticleType.BUBBLE, 4);
        this.registerInput(ParticleType.SPLASH, 5);
        this.registerInput(ParticleType.FISHING, 6);
        this.registerInput(ParticleType.UNDERWATER, 7);
        this.registerInput(ParticleType.DEPTH_SUSPENDED, 8);
        this.registerInput(ParticleType.CRIT, 9);
        this.registerInput(ParticleType.ENCHANTED_HIT, 10);
        this.registerInput(ParticleType.SMOKE, 11);
        this.registerInput(ParticleType.LARGE_SMOKE, 12);
        this.registerInput(ParticleType.EFFECT, 13);
        this.registerInput(ParticleType.INSTANT_EFFECT, 14);
        this.registerInput(ParticleType.ENTITY_EFFECT, 15);
        this.registerInput(ParticleType.AMBIENT_ENTITY_EFFECT, 16);
        this.registerInput(ParticleType.WITCH, 17);
        this.registerInput(ParticleType.DRIPPING_WATER, 18);
        this.registerInput(ParticleType.DRIPPING_LAVA, 19);
        this.registerInput(ParticleType.ANGRY_VILLAGER, 20);
        this.registerInput(ParticleType.HAPPY_VILLAGER, 21);
        this.registerInput(ParticleType.MYCELIUM, 22);
        this.registerInput(ParticleType.NOTE, 23);
        this.registerInput(ParticleType.PORTAL, 24);
        this.registerInput(ParticleType.ENCHANT, 25);
        this.registerInput(ParticleType.FLAME, 26);
        this.registerInput(ParticleType.LAVA, 27);
        this.registerInput(ParticleType.FOOTSTEP, 28);
        this.registerInput(ParticleType.CLOUD, 29);
        this.registerInput(ParticleType.DUST, 30);
        this.registerInput(ParticleType.ITEM_SNOWBALL, 31);
        this.registerInput(ParticleType.POOF, 32);
        this.registerInput(ParticleType.ITEM_SLIME, 33);
        this.registerInput(ParticleType.HEART, 34);
        this.registerInput(ParticleType.BARRIER, 35);
        this.registerInput(ParticleType.ITEM, 36);
        this.registerInput(ParticleType.BLOCK, 37);
        this.registerInput(ParticleType.BLOCK, 38);
        this.registerInput(ParticleType.RAIN, 39);
        this.registerInput(ParticleKey.fromId("TAKE"), 40);
        this.registerInput(ParticleType.ELDER_GUARDIAN, 41);
    }
}
