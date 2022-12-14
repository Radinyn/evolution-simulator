package evolution.simulator

class Map(private val params: SimulationParameters) {
    private val tiles: Array<Array<MapTile>>
    private val controlPos: (pos:Vector2d) -> Vector2d

    init {
        // handle MapType variants
        controlPos = when (params.mapType) {
            MapType.GLOBE -> {
                { pos -> pos } // TODO implement actual logic for this map type
            }
            MapType.PORTAL -> {
                { pos -> pos } // TODO for this too
            }
        }

        // handle GrowthType variants
        when (params.plantGrowthType) {
            GrowthType.EQUATOR -> {
                tiles = Array(params.width) {Array(params.height) {MapTile(false, 0, 0)}}
                // choosing equator to have 1/3 height positioned in center (with integer division uncertainty) spanning whole width
                val equatorHeight = params.height / 3
                // apply favor to equatorial tiles
                for (x in 0 until params.width) {
                    for (y in equatorHeight until 2*equatorHeight) {
                        tiles[x][y].favor = 10
                    }
                }
            }
            GrowthType.CORPSES -> {
                tiles = Array(params.width) {Array(params.height) {MapTile(false, 0, 1)}}
            }
        }
    }

    fun getTile(pos: Vector2d): MapTile {
        return tiles[pos.x][pos.y]
    }

    fun place(animal: Animal) {
        val pos = controlPos(animal.animalPositon)
        tiles[pos.x][pos.y].animalEnterBuffer(animal)
        tiles[pos.x][pos.y].animalEnterBufferApply()
    }

    fun movePhase() {
        tiles.iterator().forEach { column ->
            column.iterator().forEach {
                it.animals.iterator().forEach {
                        animal -> run {
                        val newPos = controlPos(animal.move())
                        tiles[newPos.x][newPos.y].animalEnterBuffer(animal)
                        it.animalLeave(animal)
                    }
                }
        }}

        tiles.iterator().forEach { column ->
            column.iterator().forEach {
            it.animalEnterBufferApply()
        }}
    }

    fun plantGrowthPhase() {

    }
}
