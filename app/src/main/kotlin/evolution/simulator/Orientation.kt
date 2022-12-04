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

    fun next(orientation: Orientation): Orientation {
        return orientation + 1;
    }

    fun prev(orientation: Orientation): Orientation {
        return orientation - 1;
    }
}