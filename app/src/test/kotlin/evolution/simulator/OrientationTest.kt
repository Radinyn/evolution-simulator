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

    @Test fun toVec() {
        // given
        val orient: Orientation = Orientation.NORTH
        // then
        assertEquals(Vector2d(0,0),
            orient.plus(0).toVec() +
                    orient.plus(1).toVec() +
                    orient.plus(2).toVec() +
                    orient.plus(3).toVec() +
                    orient.plus(4).toVec() +
                    orient.plus(5).toVec() +
                    orient.plus(6).toVec() +
                    orient.plus(7).toVec()
        )
    }

    @Test fun opposite() {
        assertEquals(Orientation.SOUTH, Orientation.NORTH.opposite())
        assertEquals(Orientation.SOUTH_WEST, Orientation.NORTH_EAST.opposite())
        assertEquals(Orientation.WEST, Orientation.EAST.opposite())
        assertEquals(Orientation.WEST_NORTH, Orientation.EAST_SOUTH.opposite())
        assertEquals(Orientation.NORTH, Orientation.SOUTH.opposite())
        assertEquals(Orientation.NORTH_EAST, Orientation.SOUTH_WEST.opposite())
        assertEquals(Orientation.EAST, Orientation.WEST.opposite())
        assertEquals(Orientation.EAST_SOUTH, Orientation.WEST_NORTH.opposite())
    }
}