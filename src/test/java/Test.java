import eu.mshade.enderframe.item.ItemFlag;
import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;

import java.util.List;

public class Test {


    public Test() {

        EndermanMaterialWrapper endermanMaterialWrapper = new EndermanMaterialWrapper();
        System.out.println(endermanMaterialWrapper.reverse(MaterialKey.from(2,0, null)));
        System.out.println(endermanMaterialWrapper.wrap(Material.GRASS));

        System.out.println(ItemFlag.toByte(List.of(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS)));
        System.out.println(ItemFlag.fromByte(12));
    }

    public static void main(String[] args) {
        new Test();
    }

}
