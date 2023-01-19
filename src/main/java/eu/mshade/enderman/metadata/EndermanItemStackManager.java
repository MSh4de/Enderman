package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.inventory.EquipmentSlot;
import eu.mshade.enderframe.item.*;
import eu.mshade.enderframe.metadata.attribute.AttributeKey;
import eu.mshade.enderframe.item.ItemStackMetadataKey;
import eu.mshade.enderframe.mojang.NamespacedKey;
import eu.mshade.enderframe.wrapper.WrapperRepository;
import eu.mshade.enderman.metadata.itemstack.*;
import eu.mshade.enderman.wrapper.EndermanContextWrapper;

public class EndermanItemStackManager extends ItemStackMetadataWrapperManager {


    public EndermanItemStackManager(WrapperRepository wrapperRepository) {

        Wrapper<AttributeKey, String> attributeKeyStringWrapper = (Wrapper<AttributeKey, String>) wrapperRepository.get(EndermanContextWrapper.ATTRIBUTE_KEY);
        Wrapper<EquipmentSlot, String> equipmentSlotStringWrapper = (Wrapper<EquipmentSlot, String>) wrapperRepository.get(EndermanContextWrapper.EQUIPMENT_SLOT);
        Wrapper<MaterialKey, NamespacedKey> namespacedKeyWrapper = (Wrapper<MaterialKey, NamespacedKey>) wrapperRepository.get(EndermanContextWrapper.NAMESPACED_KEY);


        this.registerBuffer(ItemStackMetadataKey.UNBREAKABLE, new UnbreakableItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.LORE, new LoreItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.NAME, new NameItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.CAN_PLACE_ON, new CanPlaceOnItemStackMetadataWrapper(namespacedKeyWrapper));
        this.registerBuffer(ItemStackMetadataKey.CAN_DESTROY, new CanDestroyItemStackMetadataWrapper(namespacedKeyWrapper));
        this.registerBuffer(ItemStackMetadataKey.SKULL_OWNER, new SkullOwnerItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.HIDE_FLAGS, new HideFlagsItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.COLOR, new ColorItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.SKULL_OWNER, new SkullOwnerItemStackMetadataWrapper());
        this.registerBuffer(ItemStackMetadataKey.ATTRIBUTE_MODIFIERS, new AttributeModifiersItemStackMetadataWrapper(equipmentSlotStringWrapper, attributeKeyStringWrapper));
    }
}
