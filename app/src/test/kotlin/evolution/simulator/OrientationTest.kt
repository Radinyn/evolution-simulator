package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals

class OrientationTest {
    @Test fun add() {
        // given
        val orient: Orientation = Orientation.NORTH
        // then
        assertEquals(Orientation.EAST_SOUTH, orient + 3)
    }

    @Test fun subtract() {
        // given
        val orient: Orientation = Orientation.SOUTH
        // then
        assertEquals(Orientation.NORTH_EAST, orient - 3)
    }

    @Test fun addOverflow() {
        // given
        val orient: Orientation = Orientation.WEST
        // then
        assertEquals(Orientation.NORTH_EAST, orient + 3)
    }

    @Test fun subtractOverflow() {
        // given
        val orient: Orientation = Orientation.NORTH
        // then
        assertEquals(Orientation.SOUTH_WEST, orient - 3)
    }
}