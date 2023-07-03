package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.attribute.Attribute;
import eu.mshade.enderframe.attribute.AttributeKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanAttributeKeyWrapper extends Wrapper<AttributeKey, String> {

    public EndermanAttributeKeyWrapper() {
        this.register(Attribute.MAX_HEALTH, "generic.maxHealth");
        this.register(Attribute.FOLLOW_RANGE, "generic.followRange");
        this.register(Attribute.KNOCKBACK_RESISTANCE, "generic.knockbackResistance");
        this.register(Attribute.MOVEMENT_SPEED, "generic.movementSpeed");
        this.register(Attribute.ATTACK_DAMAGE, "generic.attackDamage");
        this.register(Attribute.HORSE_JUMP_STRENGTH, "horse.jumpStrength");
        this.register(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, "zombie.spawnReinforcements");

    }
}
