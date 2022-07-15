package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.metadata.attribute.Attribute;
import eu.mshade.enderframe.metadata.attribute.AttributeKey;
import eu.mshade.enderframe.wrapper.Wrapper;

public class EndermanAttributeKeyWrapper extends Wrapper<AttributeKey, String> {

    public EndermanAttributeKeyWrapper() {
        this.register(Attribute.MOVEMENT_SPEED, "generic.movementSpeed");
        this.register(Attribute.MAX_HEALTH, "generic.maxHealth");
    }
}
