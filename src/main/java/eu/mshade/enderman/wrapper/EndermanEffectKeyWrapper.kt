package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.effect.PotionEffectKey
import eu.mshade.enderframe.effect.PotionEffectType
import eu.mshade.enderframe.wrapper.Wrapper

class EndermanEffectKeyWrapper : Wrapper<PotionEffectKey, Int>() {

    init {
        register(PotionEffectType.SPEED, 1)
        register(PotionEffectType.SLOWNESS, 2)
        register(PotionEffectType.HASTE, 3)
        register(PotionEffectType.MINING_FATIGUE, 4)
        register(PotionEffectType.STRENGTH, 5)
        register(PotionEffectType.INSTANT_HEALTH, 6)
        register(PotionEffectType.INSTANT_DAMAGE, 7)
        register(PotionEffectType.JUMP_BOOST, 8)
        register(PotionEffectType.NAUSEA, 9)
        register(PotionEffectType.REGENERATION, 10)
        register(PotionEffectType.RESISTANCE, 11)
        register(PotionEffectType.FIRE_RESISTANCE, 12)
        register(PotionEffectType.WATER_BREATHING, 13)
        register(PotionEffectType.INVISIBILITY, 14)
        register(PotionEffectType.BLINDNESS, 15)
        register(PotionEffectType.NIGHT_VISION, 16)
        register(PotionEffectType.HUNGER, 17)
        register(PotionEffectType.WEAKNESS, 18)
        register(PotionEffectType.POISON, 19)
        register(PotionEffectType.WITHER, 20)
        register(PotionEffectType.HEALTH_BOOST, 21)
        register(PotionEffectType.ABSORPTION, 22)
        register(PotionEffectType.SATURATION, 23)
    }

}