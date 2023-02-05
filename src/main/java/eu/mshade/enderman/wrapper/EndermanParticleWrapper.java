package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.particle.ParticleType;
import eu.mshade.enderframe.particle.ParticleKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanParticleWrapper extends Wrapper<ParticleKey, Integer> {

    public EndermanParticleWrapper() {
        this.registerMapping(ParticleType.POOF, 0);
        this.registerMapping(ParticleType.EXPLOSION, 1);
        this.registerMapping(ParticleType.EXPLOSION_EMITTER, 2);
        this.registerMapping(ParticleType.FIREWORK, 3);
        this.registerMapping(ParticleType.BUBBLE, 4);
        this.registerMapping(ParticleType.SPLASH, 5);
        this.registerMapping(ParticleType.FISHING, 6);
        this.registerMapping(ParticleType.UNDERWATER, 7);
        this.registerMapping(ParticleType.DEPTH_SUSPENDED, 8);
        this.registerMapping(ParticleType.CRIT, 9);
        this.registerMapping(ParticleType.ENCHANTED_HIT, 10);
        this.registerMapping(ParticleType.SMOKE, 11);
        this.registerMapping(ParticleType.LARGE_SMOKE, 12);
        this.registerMapping(ParticleType.EFFECT, 13);
        this.registerMapping(ParticleType.INSTANT_EFFECT, 14);
        this.registerMapping(ParticleType.ENTITY_EFFECT, 15);
        this.registerMapping(ParticleType.AMBIENT_ENTITY_EFFECT, 16);
        this.registerMapping(ParticleType.WITCH, 17);
        this.registerMapping(ParticleType.DRIPPING_WATER, 18);
        this.registerMapping(ParticleType.DRIPPING_LAVA, 19);
        this.registerMapping(ParticleType.ANGRY_VILLAGER, 20);
        this.registerMapping(ParticleType.HAPPY_VILLAGER, 21);
        this.registerMapping(ParticleType.MYCELIUM, 22);
        this.registerMapping(ParticleType.NOTE, 23);
        this.registerMapping(ParticleType.PORTAL, 24);
        this.registerMapping(ParticleType.ENCHANT, 25);
        this.registerMapping(ParticleType.FLAME, 26);
        this.registerMapping(ParticleType.LAVA, 27);
        this.registerMapping(ParticleType.FOOTSTEP, 28);
        this.registerMapping(ParticleType.CLOUD, 29);
        this.registerMapping(ParticleType.DUST, 30);
        this.registerMapping(ParticleType.ITEM_SNOWBALL, 31);
        this.registerMapping(ParticleType.POOF, 32);
        this.registerMapping(ParticleType.ITEM_SLIME, 33);
        this.registerMapping(ParticleType.HEART, 34);
        this.registerMapping(ParticleType.BARRIER, 35);
        this.registerMapping(ParticleType.ITEM, 36);
        this.registerMapping(ParticleType.BLOCK, 37);
        this.registerMapping(ParticleType.BLOCK, 38);
        this.registerMapping(ParticleType.RAIN, 39);
        this.registerMapping(ParticleKey.fromId("TAKE"), 40);
        this.registerMapping(ParticleType.ELDER_GUARDIAN, 41);
    }
}
