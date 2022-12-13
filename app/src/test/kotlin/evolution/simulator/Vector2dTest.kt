package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals

class Vector2dTest {

    @Test fun add() {
        // given
        val vec1 = Vector2d(1,3)
        val vec2 = Vector2d(3,5)
        // then
        assertEquals(Vector2d(4,8), vec1 + vec2)
    }

    @Test fun subtract() {
        // given
        val vec1 = Vector2d(1,3)
        val vec2 = Vector2d(3,5)
        // then
        assertEquals(Vector2d(-2,-2), vec1 - vec2)
    }

    @Test fun addAssign() {
        // given
        val vec1 = Vector2d(1,3)
        val vec2 = Vector2d(3,5)
        // then
        vec1+=vec2
        assertEquals(Vector2d(4,8), vec1)
    }

    @Test fun subtractAssign() {
        // given
        val vec1 = Vector2d(1,3)
        val vec2 = Vector2d(3,5)
        // then
        vec1-=vec2
        assertEquals(Vector2d(-2,-2), vec1)
    }
}