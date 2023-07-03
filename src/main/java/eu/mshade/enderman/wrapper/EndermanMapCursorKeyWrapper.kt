package eu.mshade.enderman.wrapper

import eu.mshade.enderframe.map.MapCursorKey
import eu.mshade.enderframe.map.MapCursorType
import eu.mshade.enderframe.wrapper.Wrapper

class EndermanMapCursorKeyWrapper: Wrapper<MapCursorKey, Int>() {

    init {
        register(MapCursorType.WHITE_POINTER, 0)
        register(MapCursorType.GREEN_POINTER, 1)
        register(MapCursorType.RED_POINTER, 2)
        register(MapCursorType.BLUE_POINTER, 3)
        register(MapCursorType.WHITE_CROSS, 4)
    }

}