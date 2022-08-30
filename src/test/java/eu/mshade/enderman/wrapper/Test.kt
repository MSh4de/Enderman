package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.item.Material
import eu.mshade.enderframe.item.MaterialKey
import eu.mshade.enderframe.wrapper.ContextWrapper
import eu.mshade.enderframe.wrapper.Wrapper
import eu.mshade.enderframe.wrapper.WrapperRepository

fun main() {
    val wrapperRepository = WrapperRepository()
    val wrapper = Wrapper<MaterialKey, MaterialKey>()
    wrapper.register(Material.STONE, MaterialKey.from(76))

    wrapperRepository.register(ContextWrapper.MATERIAL_KEY, wrapper)

    val materialKeyWrapper: Wrapper<MaterialKey, MaterialKey>? =
        wrapperRepository.get(ContextWrapper.MATERIAL_KEY) as? Wrapper<MaterialKey, MaterialKey>
    println(materialKeyWrapper?.wrap(Material.STONE))


}