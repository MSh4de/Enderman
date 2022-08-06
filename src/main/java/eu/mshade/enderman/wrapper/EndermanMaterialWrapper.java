package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.wrapper.Wrapper;


public class EndermanMaterialWrapper extends Wrapper<MaterialKey, MaterialKey> {

    /**
     * Class for wrapping a MaterialKey for protocol 1.8.9
     */

    public EndermanMaterialWrapper() {
        //list minecraft materials id

        register(Material.AIR, MaterialKey.from(0));
        register(Material.STONE, MaterialKey.from(1, 0));
        register(Material.GRANITE, MaterialKey.from(1, 1));
        register(Material.POLISHED_GRANITE, MaterialKey.from(1, 2));
        register(Material.DIORITE, MaterialKey.from(1, 3));
        register(Material.POLISHED_DIORITE, MaterialKey.from(1, 4));
        register(Material.ANDESITE, MaterialKey.from(1, 5));
        register(Material.POLISHED_ANDESITE, MaterialKey.from(1, 6));

        register(Material.GRASS, MaterialKey.from(2));

        register(Material.DIRT, MaterialKey.from(3));
        register(Material.COARSE_DIRT, MaterialKey.from(3, 1));
        register(Material.PODZOL, MaterialKey.from(3, 2));

        register(Material.COBBLESTONE, MaterialKey.from(4));

        register(Material.OAK_WOOD_PLANKS, MaterialKey.from(5));



        register(Material.OAK_WOOD, MaterialKey.from(17));

        register(Material.WOODEN_PICKAXE, MaterialKey.from(270));
        register(Material.LEATHER_HELMET, MaterialKey.from(298));
        register(Material.PLAYER_HEAD, MaterialKey.from(397, 3));



    }


}
