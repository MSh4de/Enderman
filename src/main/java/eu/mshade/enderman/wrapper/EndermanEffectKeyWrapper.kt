package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.effect.Effect
import eu.mshade.enderframe.effect.EffectKey
import eu.mshade.enderframe.effect.EffectType
import eu.mshade.enderframe.wrapper.Wrapper

class EndermanEffectKeyWrapper : Wrapper<EffectKey, Int>() {

    init {
        register(EffectType.SPEED, 1)
        register(EffectType.SLOWNESS, 2)
        register(EffectType.HASTE, 3)
        register(EffectType.MINING_FATIGUE, 4)
        register(EffectType.STRENGTH, 5)
        register(EffectType.INSTANT_HEALTH, 6)
        register(EffectType.INSTANT_DAMAGE, 7)
        register(EffectType.JUMP_BOOST, 8)
        register(EffectType.NAUSEA, 9)
        register(EffectType.REGENERATION, 10)
        register(EffectType.RESISTANCE, 11)
        register(EffectType.FIRE_RESISTANCE, 12)
        register(EffectType.WATER_BREATHING, 13)
        register(EffectType.INVISIBILITY, 14)
        register(EffectType.BLINDNESS, 15)
        register(EffectType.NIGHT_VISION, 16)
        register(EffectType.HUNGER, 17)
        register(EffectType.WEAKNESS, 18)
        register(EffectType.POISON, 19)
        register(EffectType.WITHER, 20)
        register(EffectType.HEALTH_BOOST, 21)
        register(EffectType.ABSORPTION, 22)
        register(EffectType.SATURATION, 23)
    }

}