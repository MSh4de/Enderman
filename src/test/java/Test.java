import eu.mshade.enderframe.item.Material;
import eu.mshade.enderframe.item.MaterialKey;
import eu.mshade.enderman.wrapper.EndermanMaterialWrapper;

public class Test {


    public Test() {

        EndermanMaterialWrapper endermanMaterialWrapper = new EndermanMaterialWrapper();
        System.out.println(endermanMaterialWrapper.reverse(MaterialKey.from(1, 1)));
        System.out.println(endermanMaterialWrapper.reverse(Material.GRANITE));
    }

    public static void main(String[] args) {
        new Test();
    }

}
