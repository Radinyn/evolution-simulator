package evolution.simulator

import java.util.*

class Vector2d(var x: Int, var y: Int) {

    operator fun plus(other: Vector2d): Vector2d {
        return Vector2d(this.x + other.x, this.y + other.y)
    }

    operator fun minus(other: Vector2d): Vector2d {
        return Vector2d(this.x - other.x, this.y - other.y)
    }

    operator fun plusAssign(other: Vector2d) {
        this.x += other.x
        this.y += other.y
    }

    operator fun minusAssign(other: Vector2d) {
        this.x -= other.x
        this.y -= other.y
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Vector2d
        if (x != other.x || y != other.y) return false
        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(this.x, this.y)
    }

    override fun toString(): String {
        return "[$x, $y]"
    }
}