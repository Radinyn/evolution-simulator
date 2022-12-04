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

    @Test fun next() {
        // given
        var orient: Orientation = Orientation.NORTH
        // then
        orient = orient.next()
        assertEquals(Orientation.NORTH_EAST, orient)
        orient = orient.next()
        assertEquals(Orientation.EAST, orient)
        orient = orient.next()
        assertEquals(Orientation.EAST_SOUTH, orient)
        orient = orient.next()
        assertEquals(Orientation.SOUTH, orient)
        orient = orient.next()
        assertEquals(Orientation.SOUTH_WEST, orient)
        orient = orient.next()
        assertEquals(Orientation.WEST, orient)
        orient = orient.next()
        assertEquals(Orientation.WEST_NORTH, orient)
        orient = orient.next()
        assertEquals(Orientation.NORTH, orient)
    }

    @Test fun prev() {
        // given
        var orient: Orientation = Orientation.NORTH
        // then
        orient = orient.prev()
        assertEquals(Orientation.WEST_NORTH, orient)
        orient = orient.prev()
        assertEquals(Orientation.WEST, orient)
        orient = orient.prev()
        assertEquals(Orientation.SOUTH_WEST, orient)
        orient = orient.prev()
        assertEquals(Orientation.SOUTH, orient)
        orient = orient.prev()
        assertEquals(Orientation.EAST_SOUTH, orient)
        orient = orient.prev()
        assertEquals(Orientation.EAST, orient)
        orient = orient.prev()
        assertEquals(Orientation.NORTH_EAST, orient)
        orient = orient.prev()
        assertEquals(Orientation.NORTH, orient)
    }
}