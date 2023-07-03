package eu.mshade.enderman.metadata

import eu.mshade.enderframe.attribute.AttributeKey
import eu.mshade.enderframe.effect.EffectKey
import eu.mshade.enderframe.inventory.EquipmentSlot
import eu.mshade.enderframe.item.ItemStackMetadataKey
import eu.mshade.enderframe.item.ItemStackMetadataWrapperManager
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.map.MapCursorKey
import eu.mshade.enderframe.mojang.NamespacedKey
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.enderframe.wrapper.WrapperRepository
import eu.mshade.enderman.metadata.itemstack.*
import eu.mshade.enderman.wrapper.EndermanContextWrapper

class EndermanItemStackManager(wrapperRepository: WrapperRepository) : ItemStackMetadataWrapperManager() {

    init {
        val attributeKeyStringWrapper =
            wrapperRepository.get(EndermanContextWrapper.ATTRIBUTE_KEY) as Wrapper<AttributeKey, String>?
        val equipmentSlotStringWrapper =
            wrapperRepository.get(EndermanContextWrapper.EQUIPMENT_SLOT) as Wrapper<EquipmentSlot, String>?
        val namespacedKeyWrapper =
            wrapperRepository.get(EndermanContextWrapper.NAMESPACED_KEY) as Wrapper<MaterialKey, NamespacedKey>?
        val effectTypeWrapper = wrapperRepository.get(EndermanContextWrapper.EFFECT_TYPE) as Wrapper<EffectKey, Int>?
        val cursorTypeWrapper =
            wrapperRepository.get(EndermanContextWrapper.MAP_CURSOR_TYPE) as Wrapper<MapCursorKey, Int>?

        registerBuffer(ItemStackMetadataKey.UNBREAKABLE, UnbreakableItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.LORE, LoreItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.NAME, NameItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.CAN_PLACE_ON, CanPlaceOnItemStackMetadataWrapper(namespacedKeyWrapper))
        registerBuffer(ItemStackMetadataKey.CAN_DESTROY, CanDestroyItemStackMetadataWrapper(namespacedKeyWrapper))
        registerBuffer(ItemStackMetadataKey.SKULL_OWNER, SkullOwnerItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.HIDE_FLAGS, HideFlagsItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.COLOR, ColorItemStackMetadataWrapper())
        registerBuffer(ItemStackMetadataKey.SKULL_OWNER, SkullOwnerItemStackMetadataWrapper())
        registerBuffer(
            ItemStackMetadataKey.ATTRIBUTE_MODIFIERS,
            AttributeModifiersItemStackMetadataWrapper(equipmentSlotStringWrapper, attributeKeyStringWrapper)
        )
        registerBuffer(
            ItemStackMetadataKey.CUSTOM_POTION_EFFECTS,
            CustomPotionEffectsItemStackMetadataWrapper(effectTypeWrapper)
        )
        registerBuffer(
            ItemStackMetadataKey.MAP_DECORATIONS, MapDecorationsItemStackMetadataWrapper(cursorTypeWrapper))
        registerBuffer(ItemStackMetadataKey.MAP_IS_SCALING, MapIsScalingItemStackMetadataWrapper())
    }
}
