package eu.mshade.enderman.metadata;

import eu.mshade.enderframe.item.*;
import eu.mshade.enderframe.metadata.itemstack.ItemStackMetadataKey;
import eu.mshade.enderman.metadata.itemstack.*;
import eu.mshade.enderman.wrapper.EndermanNamespacedKeyWrapper;

public class EndermanItemStackManager extends ItemStackMetadataManager {

    private EndermanNamespacedKeyWrapper endermanNamespacedKeyWrapper = new EndermanNamespacedKeyWrapper();

    public EndermanItemStackManager() {
        this.registerBuffer(ItemStackMetadataKey.UNBREAKABLE, new UnbreakableItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.LORE, new LoreItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.NAME, new NameItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.CAN_PLACE_ON, new CanPlaceOnItemStackMetadataBuffer(endermanNamespacedKeyWrapper));
        this.registerBuffer(ItemStackMetadataKey.CAN_DESTROY, new CanDestroyItemStackMetadataBuffer(endermanNamespacedKeyWrapper));
        this.registerBuffer(ItemStackMetadataKey.SKULL_OWNER, new SkullOwnerItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.HIDE_FLAGS, new HideFlagsItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.COLOR, new ColorItemStackMetadataBuffer());
        this.registerBuffer(ItemStackMetadataKey.SKULL_OWNER, new SkullOwnerItemStackMetadataBuffer());
    }
}
