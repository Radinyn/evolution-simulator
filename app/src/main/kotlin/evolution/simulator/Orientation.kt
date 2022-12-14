package evolution.simulator

enum class Orientation {
    NORTH,
    NORTH_EAST,
    EAST,
    EAST_SOUTH,
    SOUTH,
    SOUTH_WEST,
    WEST,
    WEST_NORTH;

    operator fun plus(step: Int): Orientation {
        return Orientation.values()[(this.ordinal + step + enumValues<Orientation>().size) % enumValues<Orientation>().size]
    }

    operator fun minus(step: Int): Orientation {
        return Orientation.values()[(this.ordinal - step + enumValues<Orientation>().size) % enumValues<Orientation>().size]
    }

    fun next(): Orientation {
        return this + 1
    }

    fun prev(): Orientation {
        return this - 1
    }

    fun toVec(): Vector2d {
        return when (this) {
            NORTH -> Vector2d(0,1)
            NORTH_EAST -> Vector2d(1,1)
            EAST -> Vector2d(1,0)
            EAST_SOUTH -> Vector2d(1, -1)
            SOUTH -> Vector2d(0, -1)
            SOUTH_WEST -> Vector2d(-1, -1)
            WEST -> Vector2d(-1 ,0)
            WEST_NORTH -> Vector2d(-1, 1)
        }
    }

    companion object {
        fun fromInt(value: Int): Orientation {
            assert(value < enumValues<Orientation>().size && value >= 0)
            return Orientation.values()[value]
        }
    }
}
