package eu.mshade.enderman.wrapper;

import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderframe.wrapper.Wrapper;



public class EndermanMaterialWrapper extends Wrapper<MaterialKey, MaterialKey> {


    public EndermanMaterialWrapper() {

        register(Material.AIR, Material.AIR);
        register(Material.STONE, Material.STONE);
        register(Material.GRANITE, Material.GRANITE);
        register(Material.POLISHED_GRANITE, Material.POLISHED_GRANITE);
        register(Material.DIORITE, Material.DIORITE);
        register(Material.POLISHED_DIORITE, Material.POLISHED_DIORITE);
        register(Material.ANDESITE, Material.ANDESITE);
        register(Material.POLISHED_ANDESITE, Material.POLISHED_ANDESITE);
        register(Material.GRASS, Material.GRASS);
        register(Material.DIRT, Material.DIRT);
        register(Material.COARSE_DIRT, Material.COARSE_DIRT);
        register(Material.PODZOL, Material.PODZOL);

    }


}
