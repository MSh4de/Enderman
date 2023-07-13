package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.world.effect.WorldEffectKey
import eu.mshade.enderframe.world.effect.WorldEffectType
import eu.mshade.enderframe.wrapper.Wrapper

class EndermanWorldEffectWrapper : Wrapper<WorldEffectKey, Int>() {

    init {
        registerMapping(WorldEffectType.CLICK2, 1000)
        registerMapping(WorldEffectType.CLICK1, 1001)
        registerMapping(WorldEffectType.BOW_FIRE, 1002)
        registerMapping(WorldEffectType.DOOR_TOGGLE, 1003)
        registerMapping(WorldEffectType.EXTINGUISH, 1004)
        registerMapping(WorldEffectType.RECORD_PLAY, 1005)
        registerMapping(WorldEffectType.GHAST_SHRIEK, 1007)
        registerMapping(WorldEffectType.GHAST_SHOOT, 1008)
        registerMapping(WorldEffectType.BLAZE_SHOOT, 1009)
        registerMapping(WorldEffectType.ZOMBIE_CHEW_WOODEN_DOOR, 1010)
        registerMapping(WorldEffectType.ZOMBIE_CHEW_IRON_DOOR, 1011)
        registerMapping(WorldEffectType.ZOMBIE_DESTROY_DOOR, 1012)
        registerMapping(WorldEffectType.SMOKE, 2000)
        registerMapping(WorldEffectType.STEP_SOUND, 2001)
        registerMapping(WorldEffectType.POTION_BREAK, 2002)
        registerMapping(WorldEffectType.ENDER_SIGNAL, 2003)
        registerMapping(WorldEffectType.MOBSPAWNER_FLAMES, 2004)    }
}